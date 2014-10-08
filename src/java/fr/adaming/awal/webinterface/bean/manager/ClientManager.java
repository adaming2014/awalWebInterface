/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IClientController;
import fr.adaming.awal.webinterface.bean.form.AddressParameters;
import fr.adaming.awal.webinterface.bean.form.ClientParameters;
import fr.adaming.awal.webinterface.bean.form.UserParameters;
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
public class ClientManager extends GenericManager {

    @ManagedProperty("#{authManager}")
    private AuthManager authManager;

    public void update() {
    }

    public void resetFields() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserParameters userParameters = getManagedBean(context, "userParameters", UserParameters.class);
        ClientParameters clientParameters = getManagedBean(context, "clientParameters", ClientParameters.class);
        AddressParameters addressParameters = getManagedBean(context, "addressParameters", AddressParameters.class);

        IClientController controller = (IClientController) springContext.getBean("clientController");
    }

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

}
