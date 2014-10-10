/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IClientController;
import fr.adaming.awal.controller.interfaces.IDeviceRepairController;
import fr.adaming.awal.entity.Device;
import fr.adaming.awal.entity.Devicerepair;
import fr.adaming.awal.entity.Modelpackage;
import fr.adaming.awal.webinterface.util.FacesMessageUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author INTI0217
 */
@ManagedBean
@SessionScoped
public class DeviceRepairManager extends GenericManager {

    private static final String NAVIGATION_HOME_CLIENT = "pretty:home";

    private Modelpackage modelPackage;
    private Devicerepair deviceRepair;
    private Device device;

    @ManagedProperty("#{authManager}")
    AuthManager authManager;

    /**
     * Creates a new instance of DeviceRepairManager
     */
    public DeviceRepairManager() {
        deviceRepair = new Devicerepair();
    }

    public String add() {
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

    public List<Devicerepair> getDevicesRepairByClient() {
        IClientController clientController = (IClientController) springContext.getBean("clientController");
        IDeviceRepairController deviceController = (IDeviceRepairController) springContext.getBean("deviceRepairController");
        return deviceController.getDevicesRepairByClient(clientController.getById(authManager.getClientId()));
    }

    public Modelpackage getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(Modelpackage modelPackage) {
        this.modelPackage = modelPackage;
    }

    public Devicerepair getDeviceRepair() {
        return deviceRepair;
    }

    public void setDeviceRepair(Devicerepair deviceRepair) {
        this.deviceRepair = deviceRepair;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

}
