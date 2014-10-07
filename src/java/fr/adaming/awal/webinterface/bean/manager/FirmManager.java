/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IFirmController;
import fr.adaming.awal.entity.Address;
import fr.adaming.awal.entity.Firm;
import fr.adaming.awal.webinterface.bean.form.AddressParameters;
import fr.adaming.awal.webinterface.bean.form.FirmParameters;
import fr.adaming.awal.webinterface.util.FacesMessageUtil;
import fr.adaming.awal.webinterface.util.FileUtil;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@SessionScoped
public class FirmManager extends GenericManager {

    private static final String INIT_PARAM_PATH_IMAGE = "path_image";
    private static final String INIT_PARAM_PATH_CSS = "path_css";

    private static final String PAGE_FIRM_LIST_REDIRECT = "firm_list_redirect";

    @ManagedProperty("#{authManager}")
    private AuthManager authManager;

    private int firmId;

    private final String image_path;
    private final String css_path;

    /**
     * Creates a new instance of SigninManager
     */
    public FirmManager() {
        image_path = FacesContext.getCurrentInstance().getExternalContext().getRealPath(FacesContext.getCurrentInstance().getExternalContext().getInitParameter(INIT_PARAM_PATH_IMAGE));
        css_path = FacesContext.getCurrentInstance().getExternalContext().getRealPath(FacesContext.getCurrentInstance().getExternalContext().getInitParameter(INIT_PARAM_PATH_CSS));
    }

    public String add() {
        FacesContext context = FacesContext.getCurrentInstance();
        FirmParameters parameters = getManagedBean(context, "firmParameters", FirmParameters.class);

        IFirmController controller = (IFirmController) springContext.getBean("firmController");

        Address address = new Address();
        address.setCity(parameters.getAddress().getCity());
        address.setPostcode(parameters.getAddress().getPostcode());
        address.setStreet(parameters.getAddress().getStreet());

        Firm firm = new Firm();
        firm.setName(parameters.getName());
        firm.setAddress(address);
        firm.setPhone(parameters.getPhone());
        firm.setLogoPath(parameters.getLogo().getFileName());
        firm.setCssPath(parameters.getTheme().getFileName());

        if (!controller.create(firm)) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DATABASE_ERROR);
            return null;
        }

        if (parameters.getLogo().getSize() > 0) {
            FileUtil.saveFile(firm.getId(), image_path, parameters.getLogo(), "logo.png");
        }

        if (parameters.getTheme().getSize() > 0) {
            FileUtil.saveFile(firm.getId(), css_path, parameters.getTheme(), "theme.css");
        }

        return PAGE_FIRM_LIST_REDIRECT;
    }

    public void update() {
        FacesContext context = FacesContext.getCurrentInstance();
        FirmParameters parameters = getManagedBean(context, "firmParameters", FirmParameters.class);
        AddressParameters parametersAddress = parameters.getAddress();

        IFirmController controller = (IFirmController) springContext.getBean("firmController");

        Firm firm = controller.getById(firmId);
        if (firm == null) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_FIRM_NOT_FOUND);
            return;
        }

        Address firmAddress = firm.getAddress();
        if (firmAddress == null) {
            firmAddress = new Address();
        }

        // Update fields
        firmAddress.setCity(parametersAddress.getCity());
        firmAddress.setStreet(parametersAddress.getStreet());
        firmAddress.setPostcode(parametersAddress.getPostcode());

        firm.setAddress(firmAddress);
        firm.setName(parameters.getName());
        firm.setPhone(parameters.getPhone());
        firm.setCssPath(parameters.getTheme().getFileName());
        firm.setLogoPath(parameters.getLogo().getFileName());

        // Persist
        if (!controller.update(firm)) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DATABASE_ERROR);
            return;
        }

        context.addMessage(null, FacesMessageUtil.INFO_FIRM_UPDATED);
    }

    public void reset() {
        FacesContext context = FacesContext.getCurrentInstance();
        FirmParameters parameters = getManagedBean(context, "firmParameters", FirmParameters.class);

        IFirmController controller = (IFirmController) springContext.getBean("firmController");

        Firm firm = controller.getById(firmId);
        if (firm == null) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_FIRM_NOT_FOUND);
            return;
        }

        parameters.setName(firm.getName());
        parameters.setPhone(firm.getPhone());

        Address firmAddress = firm.getAddress();

        if (firmAddress != null) {
            parameters.getAddress().setCity(firmAddress.getCity());
            parameters.getAddress().setStreet(firmAddress.getStreet());
            parameters.getAddress().setPostcode(firmAddress.getPostcode());
        }
    }

    public String delete() {
        FacesContext context = FacesContext.getCurrentInstance();
        FirmParameters parameters = getManagedBean(context, "firmParameters", FirmParameters.class);

        IFirmController controller = (IFirmController) springContext.getBean("firmController");

        Firm firm = controller.getById(firmId);
        if (firm == null) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_FIRM_NOT_FOUND);
            return null;
        }

        boolean result = controller.delete(firm);
        if (!result) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DATABASE_ERROR);
            return null;
        }

        return PAGE_FIRM_LIST_REDIRECT;
    }

    public List<Firm> getAll(String firmName) {
        List<Firm> firms = getAll();

        String firmNameLowerCase = firmName.toLowerCase();

        return firms.stream().filter((firm) -> firm.getName().toLowerCase().startsWith(firmNameLowerCase)).collect(Collectors.toList());
    }

    public List<Firm> getAll() {
        IFirmController controller = (IFirmController) springContext.getBean("firmController");

        return controller.getAll();
    }

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }

}
