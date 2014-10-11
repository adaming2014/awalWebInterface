/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.list;

import fr.adaming.awal.controller.interfaces.IClientController;
import fr.adaming.awal.controller.interfaces.IDeviceRepairController;
import fr.adaming.awal.entity.Devicerepair;
import fr.adaming.awal.webinterface.bean.manager.AuthManager;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@RequestScoped
public class DeviceRepairByClient extends GenericList {

    @ManagedProperty("#{authManager}")
    AuthManager authManager;

    List<Devicerepair> devices;

    public List<Devicerepair> getDevicesRepair() {
        return devices;
    }

    @Override
    protected void load() {
        IClientController clientController = (IClientController) springContext.getBean("clientController");
        IDeviceRepairController deviceRepairController = (IDeviceRepairController) springContext.getBean("deviceRepairController");

        devices = deviceRepairController.getDevicesRepairByClient(clientController.getById(authManager.getClientId()));
    }

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

}
