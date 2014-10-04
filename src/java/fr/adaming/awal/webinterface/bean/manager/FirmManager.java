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

    private static final String PAGE_INDEX_REDIRECT = "index_redirect";

    @ManagedProperty("#{authManager}")
    private AuthManager authManager;

    private int firmId;

    /**
     * Creates a new instance of SigninManager
     */
    public FirmManager() {
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
        firm.setLogoPath(parameters.getLogo());
        firm.setCssPath(parameters.getTheme());

        if (!controller.create(firm)) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DATABASE_ERROR);
            return null;
        }

        return PAGE_INDEX_REDIRECT;
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
        firm.setCssPath(parameters.getTheme());
        firm.setLogoPath(parameters.getLogo());

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
        parameters.setLogo(firm.getLogoPath());
        parameters.setTheme(firm.getCssPath());
        parameters.setPhone(firm.getPhone());

        Address firmAddress = firm.getAddress();

        if (firmAddress != null) {
            parameters.getAddress().setCity(firmAddress.getCity());
            parameters.getAddress().setStreet(firmAddress.getStreet());
            parameters.getAddress().setPostcode(firmAddress.getPostcode());
        }
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
