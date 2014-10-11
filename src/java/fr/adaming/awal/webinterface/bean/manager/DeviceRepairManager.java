/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IDeviceRepairController;
import fr.adaming.awal.entity.Device;
import fr.adaming.awal.entity.Devicerepair;
import fr.adaming.awal.entity.Modelpackage;
import fr.adaming.awal.webinterface.util.FacesMessageUtil;
import javax.annotation.PostConstruct;
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
    private Device device;

    @ManagedProperty("#{authManager}")
    AuthManager authManager;

    IDeviceRepairController deviceRepairController;

    @Override
    @PostConstruct
    protected void init() {
        super.init();

        deviceRepairController = (IDeviceRepairController) springContext.getBean("deviceRepairController");
    }

    public String add() {
        FacesContext context = FacesContext.getCurrentInstance();

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

    public void setRepairingState(final Devicerepair devicerepair) {
        deviceRepairController.setRepairingState(devicerepair);
    }

    public void setRepairedState(final Devicerepair devicerepair) {
        deviceRepairController.setRepairedState(devicerepair);
    }

    public void setClosedState(final Devicerepair devicerepair) {
        deviceRepairController.setClosedState(devicerepair);
    }

    public Modelpackage getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(Modelpackage modelPackage) {
        this.modelPackage = modelPackage;
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
