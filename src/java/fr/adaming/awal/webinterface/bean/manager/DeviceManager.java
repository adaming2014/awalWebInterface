/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IClientController;
import fr.adaming.awal.controller.interfaces.IDeviceController;
import fr.adaming.awal.controller.interfaces.IModelController;
import fr.adaming.awal.entity.Device;
import fr.adaming.awal.entity.Modele;
import fr.adaming.awal.webinterface.bean.form.DeviceParameters;
import fr.adaming.awal.webinterface.bean.form.ModelParameters;
import fr.adaming.awal.webinterface.util.FacesMessageUtil;
import java.io.Serializable;
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
public class DeviceManager extends GenericManager implements Serializable {

    private Device device;

    public DeviceManager() {
        device = new Device();
    }

    public String add() {
        FacesContext context = FacesContext.getCurrentInstance();
        DeviceParameters deviceParameters = getManagedBean(context, "deviceParameters", DeviceParameters.class);
        AuthManager authManager = getManagedBean(context, "authManager", AuthManager.class);
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        IClientController clientController = (IClientController) springContext.getBean("clientController");
        device.setDescription(deviceParameters.getDescription());
        device.setClient(clientController.getById(authManager.getClientId()));
        if (!deviceController.create(device)) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DEVICE_NOT_CREATE);
            return null;
        }else{
            context.addMessage(null, FacesMessageUtil.INFO_DEVICE_CREATE);
        }
        System.out.println("add size : " + deviceController.getAll().size());
        return "add";
    }

    public String addother() {
        FacesContext context = FacesContext.getCurrentInstance();
        DeviceParameters deviceParameters = getManagedBean(context, "deviceParameters", DeviceParameters.class);
        ModelParameters modelParameters = getManagedBean(context, "modelParameters", ModelParameters.class);
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        IModelController modelController = (IModelController) springContext.getBean("modelController");

        Modele modele = new Modele();
        for (Modele modele1 : modelController.getAll()) {
            if (!modelParameters.getName().equals(modele1.getName())) {
                modele.setName(modelParameters.getName());
                modele.setBrand(modelParameters.getBrand());
                modele.setDimension(modelParameters.getDimension());
                modele.setWeigth(Double.parseDouble(modelParameters.getWeigth()));
                modele.setType(modelParameters.getType());
                if (!modelController.create(modele)) {
                    context.addMessage(null, FacesMessageUtil.MESSAGE_MODEL_NOT_CREATE);
                    return null;
                }
            }
        }
        device.setDescription(deviceParameters.getDescription());
        if (!deviceController.create(device)) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DEVICE_NOT_CREATE);
            return null;
        }
        return "addother";
    }

    public List<Device> getDevicesByClient() {
        FacesContext context = FacesContext.getCurrentInstance();
        AuthManager authManager = getManagedBean(context, "authManager", AuthManager.class);
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        IClientController clientController = (IClientController) springContext.getBean("clientController");
        List<Device> devices = deviceController.getDevicesByClient(clientController.getById(authManager.getClientId()));
        deviceController.getDevicesByClient(clientController.getById(authManager.getClientId()));
        return devices;
    }

    public List<Device> getAll() {
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        return deviceController.getAll();
    }

    public void delete(int id) {
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        if (!deviceController.delete(id)) {
            System.out.println("error to delete device");
        }
    }

    public Device getById(int id) {
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        return deviceController.getById(id);
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
