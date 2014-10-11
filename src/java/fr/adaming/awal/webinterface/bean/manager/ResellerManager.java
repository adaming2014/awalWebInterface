/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IClientController;
import fr.adaming.awal.controller.interfaces.IDeviceController;
import fr.adaming.awal.entity.Address;
import fr.adaming.awal.entity.Client;
import fr.adaming.awal.entity.Device;
import fr.adaming.awal.entity.User;
import fr.adaming.awal.webinterface.bean.form.ClientParameters;
import fr.adaming.awal.webinterface.bean.form.DeviceParameters;
import fr.adaming.awal.webinterface.util.FacesMessageUtil;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author INTI0217
 */
@ManagedBean
@SessionScoped
public class ResellerManager extends GenericManager implements Serializable {

    private static final String CREATE_CLIENT_RESELLER = "pretty:home";
    private static final String GERATE_CLIENT_RESELLER = "manage";
    private static final String NAVIGATION_HOME_CLIENT = "homeResellerClient";
    private Client client;
    private Device device;

    /**
     * Creates a new instance of ResellerManager
     */
    public ResellerManager() {
        device = new Device();
    }

    public String createClient() {
        SecureRandom random = new SecureRandom();
        String password = new BigInteger(130, random).toString(32);

        FacesContext context = FacesContext.getCurrentInstance();
        AuthManager authManager = context.getApplication().evaluateExpressionGet(context, "#{authManager}", AuthManager.class);
        ClientParameters clientParameters = getManagedBean(context, "clientParameters", ClientParameters.class);

        IClientController clientController = (IClientController) springContext.getBean("clientController");

        Address address = new Address();
        address.setCity(clientParameters.getAddress().getCity());
        address.setStreet(clientParameters.getAddress().getStreet());
        address.setPostcode(clientParameters.getAddress().getPostcode());

        User user = new User();
        user.setFirstname(clientParameters.getUser().getFirstname());
        user.setLastname(clientParameters.getUser().getLastname());
        user.setMail(clientParameters.getUser().getEmail());
        user.setPassword(password);

        Client client = new Client();
        client.setAddress(address);
        client.setUser(user);
        client.setFirm(authManager.getUserReseller().getFirm());

        clientController.create(client);

        return CREATE_CLIENT_RESELLER;
    }

    public List<Client> getClientsByFirm() {
        FacesContext context = FacesContext.getCurrentInstance();
        AuthManager authManager = getManagedBean(context, "authManager", AuthManager.class);
        IClientController clientController = (IClientController) springContext.getBean("clientController");

        return clientController.getClientsByFirm(authManager.getUserReseller().getFirm());
    }

    public List<Device> getDevicesByClient() {
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        List<Device> devices = deviceController.getDevicesByClient(client);
        return devices;
    }

    public String addDeviceToClient() {
        FacesContext context = FacesContext.getCurrentInstance();
        DeviceParameters deviceParameters = getManagedBean(context, "deviceParameters", DeviceParameters.class);
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        device.setDescription(deviceParameters.getDescription());
        device.setClient(client);
        if (!deviceController.create(device)) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DEVICE_NOT_CREATE);
            return null;
        } else {
            context.addMessage(null, FacesMessageUtil.INFO_DEVICE_CREATE);
        }
        return NAVIGATION_HOME_CLIENT;
    }

    public void createPDF(Client client) {

    }

    public Client getClient() {
        return client;
    }

    public String setClient(Client client) {
        this.client = client;
        return GERATE_CLIENT_RESELLER;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

}
