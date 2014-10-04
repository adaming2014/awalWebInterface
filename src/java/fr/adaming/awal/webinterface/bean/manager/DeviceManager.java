/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.ClientController;
import fr.adaming.awal.controller.interfaces.IDeviceController;
import fr.adaming.awal.controller.interfaces.IModelController;
import fr.adaming.awal.entity.Client;
import fr.adaming.awal.entity.Device;
import fr.adaming.awal.entity.Modele;
import fr.adaming.awal.webinterface.bean.form.DeviceParameters;
import fr.adaming.awal.webinterface.bean.form.ModelParameters;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0217
 */
@ManagedBean
@SessionScoped
public class DeviceManager implements Serializable {

    ApplicationContext springContext;
    private Device device;

    @PostConstruct
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
        device = new Device();
    }

    public String add() {
        FacesContext context = FacesContext.getCurrentInstance();
        DeviceParameters deviceParameters = context.getApplication().evaluateExpressionGet(context, "#{deviceParameters}", DeviceParameters.class);
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        ClientController clientController = (ClientController) springContext.getBean("clientController");

        device.setDescription(deviceParameters.getDescription());
        device.setClient(clientController.getClientByMail("bian.loic@gmail.com"));
        if (!deviceController.create(device)) {
            System.out.println("error to create device");
            return null;
        }
        return "add";
    }

    public String addother() {
        FacesContext context = FacesContext.getCurrentInstance();
        DeviceParameters deviceParameters = context.getApplication().evaluateExpressionGet(context, "#{deviceParameters}", DeviceParameters.class);
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        ModelParameters modelParameters = context.getApplication().evaluateExpressionGet(context, "#{modelParameters}", ModelParameters.class);
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
                    System.out.println("error to create modele");
                    return null;
                }
            }
        }
        device.setDescription(deviceParameters.getDescription());
        if (!deviceController.create(device)) {
            System.out.println("error to create device");
            return null;
        }
        return "addother";
    }

    public List<Device> getDevicesByClient() {
        ClientController clientController = (ClientController) springContext.getBean("clientController");
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");

        Client client = clientController.getClientByMail("bian.loic@gmail.com");
        List<Device> devices = deviceController.getDevicesByClient(client);
        return devices;
    }

    public List<Device> getAll() {
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        return deviceController.getAll();
    }

    public Device getDevice() {
        return device;
    }
    
    public void setDevice(Device device) {
        this.device = device;
    }
    
    public void delete(int id){
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        deviceController.delete(id);
    }

    public Device getById(int id){
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        return deviceController.getById(id);
    }
}
