/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IClientController;
import fr.adaming.awal.entity.Address;
import fr.adaming.awal.entity.Client;
import fr.adaming.awal.entity.User;
import fr.adaming.awal.webinterface.bean.form.UserParameters;
import fr.adaming.awal.webinterface.util.FacesMessageUtil;
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
public class UserManager extends GenericManager {

    private static final String PAGE_INDEX_REDIRECT = "index_redirect";

    @ManagedProperty("#{authManager}")
    private AuthManager authManager;

    @ManagedProperty("#{clientManager}")
    private ClientManager clientManager;

    @ManagedProperty("#{repairerManager}")
    private RepairerManager repairerManager;

    public String signin() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserParameters signinParameters = getManagedBean(context, "userParameters", UserParameters.class);

        IClientController clientController = (IClientController) springContext.getBean("clientController");

        User user = new User();
        user.setFirstname(signinParameters.getFirstname());
        user.setLastname(signinParameters.getLastname());
        user.setMail(signinParameters.getEmail());
        user.setPassword(signinParameters.getPassword());

        Client client = new Client();
        client.setUser(user);
        client.setAddress(new Address());

        if (!clientController.create(client)) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DATABASE_ERROR);
            return null;
        }

        authManager.setUser(client);

        return PAGE_INDEX_REDIRECT;
    }

    public void reset() {
        if (authManager.isClient()) {
            clientManager.resetFields();
            return;
        }

        if (authManager.isRepairer()) {
            repairerManager.resetFields();
            return;
        }
    }

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

    public ClientManager getClientManager() {
        return clientManager;
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public RepairerManager getRepairerManager() {
        return repairerManager;
    }

    public void setRepairerManager(RepairerManager repairerManager) {
        this.repairerManager = repairerManager;
    }

}
