/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.list;

import fr.adaming.awal.controller.interfaces.IDeviceRepairController;
import fr.adaming.awal.controller.interfaces.IRepairerController;
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
public class DeviceRepairByRepairer extends GenericList {

    @ManagedProperty("#{authManager}")
    private AuthManager authManager;

    private List<Devicerepair> devices;

    public List<Devicerepair> getDevicesRepair() {
        return devices;
    }

    @Override
    protected void load() {
        IRepairerController repairerController = (IRepairerController) springContext.getBean("repairerController");
        IDeviceRepairController deviceRepairController = (IDeviceRepairController) springContext.getBean("deviceRepairController");

        devices = deviceRepairController.getDevicesRepairByRepairer(repairerController.getById(authManager.getRepairerId()));
    }

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

}
