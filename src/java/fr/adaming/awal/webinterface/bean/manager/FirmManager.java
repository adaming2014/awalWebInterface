/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IFirmController;
import fr.adaming.awal.entity.Address;
import fr.adaming.awal.entity.Firm;
import fr.adaming.awal.webinterface.bean.form.FirmParameters;
import fr.adaming.awal.webinterface.util.FacesMessageUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@SessionScoped
public class FirmManager implements Serializable {

    private static final String PAGE_INDEX_REDIRECT = "index_redirect";

    @ManagedProperty("#{authManager}")
    AuthManager authManager;

    ApplicationContext springContext;

    /**
     * Creates a new instance of SigninManager
     */
    public FirmManager() {
    }

    @PostConstruct
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
    }

    public String add() {
        FacesContext context = FacesContext.getCurrentInstance();
        FirmParameters parameters = context.getApplication().evaluateExpressionGet(context, "#{firmParameters}", FirmParameters.class);

        IFirmController controller = (IFirmController) springContext.getBean("firmController");
        if (controller == null) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_CONTROLER_NOT_FOUND);
            return null;
        }

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

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }
}
