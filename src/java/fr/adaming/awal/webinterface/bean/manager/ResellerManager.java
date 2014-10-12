/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IClientController;
import fr.adaming.awal.controller.interfaces.IDeviceController;
import fr.adaming.awal.controller.interfaces.IDeviceInsuranceController;
import fr.adaming.awal.controller.interfaces.IDeviceRepairController;
import fr.adaming.awal.entity.Address;
import fr.adaming.awal.entity.Client;
import fr.adaming.awal.entity.Device;
import fr.adaming.awal.entity.Deviceinsurance;
import fr.adaming.awal.entity.Devicerepair;
import fr.adaming.awal.entity.Modelpackage;
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
    private static final String MANAGE_CLIENT_RESELLER = "manage";
    private static final String NAVIGATION_HOME_CLIENT = "homeResellerClient";
    private static final String MANAGE_DEVICE_REPAIR = "addDeviceRepairReseller";
    private Client client;
    private Device device;
    private Modelpackage modelPackage;

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

    public List<Devicerepair> getDevicesRepairByClient() {
        IDeviceRepairController deviceController = (IDeviceRepairController) springContext.getBean("deviceRepairController");
        return deviceController.getDevicesRepairByClient(client);
    }

    public List<Deviceinsurance> getDevicesInsuranceByClient() {
        IDeviceInsuranceController deviceInsuranceController = (IDeviceInsuranceController) springContext.getBean("deviceInsuranceController");
        return deviceInsuranceController.getDevicesInsuranceByClient(client);
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

    public String addDeviceRepairToClient() {
        FacesContext context = FacesContext.getCurrentInstance();
        IDeviceRepairController deviceRepairController = (IDeviceRepairController) springContext.getBean("deviceRepairController");

        Devicerepair devicerepair = new Devicerepair();
        devicerepair.setDevice(device);
        devicerepair.setModelpackage(modelPackage);

        try {
            deviceRepairController.createDeviceRepair(devicerepair);
        } catch (IDeviceRepairController.PackageAlreadyPresentException ex) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DEVICE_REPAIR_ALREADY_EXIST);
            return null;
        }

        return NAVIGATION_HOME_CLIENT;
    }

    public Client getClient() {
        return client;
    }

    public String setClient(Client client) {
        this.client = client;
        return MANAGE_CLIENT_RESELLER;
    }

    public Device getDevice() {
        return device;
    }

    public String setDevice(Device device) {
        this.device = device;
        return MANAGE_DEVICE_REPAIR;
    }

    public Modelpackage getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(Modelpackage modelPackage) {
        this.modelPackage = modelPackage;
    }

}
