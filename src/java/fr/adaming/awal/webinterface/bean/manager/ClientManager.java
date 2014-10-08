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
import fr.adaming.awal.webinterface.bean.form.AddressParameters;
import fr.adaming.awal.webinterface.bean.form.ClientParameters;
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
public class ClientManager extends GenericManager {

    @ManagedProperty("#{authManager}")
    private AuthManager authManager;

    public void update() {
        FacesContext context = FacesContext.getCurrentInstance();
        ClientParameters clientParameters = getManagedBean(context, "clientParameters", ClientParameters.class);
        UserParameters userParameters = clientParameters.getUser();
        AddressParameters addressParameters = clientParameters.getAddress();

        IClientController controller = (IClientController) springContext.getBean("clientController");

        Client client = controller.getById(authManager.getClientId());
        if (client == null) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DATABASE_ERROR);
            return;
        }

        User user = client.getUser();
        Address address = client.getAddress();
        if (address == null) {
            address = new Address();
        }

        address.setCity(addressParameters.getCity());
        address.setStreet(addressParameters.getStreet());
        address.setPostcode(addressParameters.getPostcode());

        user.setFirstname(userParameters.getFirstname());
        user.setLastname(userParameters.getLastname());
        user.setMail(userParameters.getEmail());
        user.setPhone(userParameters.getPhone());

        client.setAddress(address);
        client.setUser(user);
        client.setNumbercard(clientParameters.getNumbercard());

        if (!controller.update(client)) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DATABASE_ERROR);
            return;
        }

        context.addMessage(null, FacesMessageUtil.INFO_USER_UPDATED);
    }

    public void resetFields() {
        FacesContext context = FacesContext.getCurrentInstance();
        ClientParameters clientParameters = getManagedBean(context, "clientParameters", ClientParameters.class);
        UserParameters userParameters = clientParameters.getUser();
        AddressParameters addressParameters = clientParameters.getAddress();

        IClientController controller = (IClientController) springContext.getBean("clientController");

        Client client = controller.getById(authManager.getClientId());
        System.out.println(authManager.getClientId() + ", " + client);
        if (client == null) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DATABASE_ERROR);
            return;
        }

        Address address = client.getAddress();
        User user = client.getUser();

        if (address != null) {
            addressParameters.setCity(address.getCity());
            addressParameters.setStreet(address.getStreet());
            addressParameters.setPostcode(address.getPostcode());
        }

        userParameters.setFirstname(user.getFirstname());
        userParameters.setLastname(user.getLastname());
        userParameters.setEmail(user.getMail());
        userParameters.setPhone(user.getPhone());

        clientParameters.setNumbercard(null);
    }

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

}
