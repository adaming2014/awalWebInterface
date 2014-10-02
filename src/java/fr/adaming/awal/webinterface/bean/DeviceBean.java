/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;


import fr.adaming.awal.controller.ClientController;
import fr.adaming.awal.controller.DeviceController;
import fr.adaming.awal.controller.interfaces.IClientController;
import fr.adaming.awal.controller.interfaces.IDeviceController;
import fr.adaming.awal.entity.Client;
import fr.adaming.awal.entity.Device;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0217
 */
@ManagedBean
@SessionScoped
public class DeviceBean implements Serializable{

    ApplicationContext context;
    private DeviceController deviceController;
    private Device device;
    private ClientController clientController;
    
    /**
     * Creates a new instance of DeviceBean
     */
    public DeviceBean() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        deviceController = (DeviceController) context.getBean("deviceController");
        clientController = (ClientController) context.getBean("clientController");
        device = new Device();
    }
    
    public List<Device> getDevicesByClient(){
        Client client = clientController.getClientByMail("bian.loic@gmail.com");
        System.out.println(client.getNumbercard());
        List<Device> devices = deviceController.getDevicesByClient(client);
        for (Device device1 : devices) {
            System.out.println(device1.getDescription());
            System.out.println(device1.getModele().getName());
        }
        return devices;
    }
    
    
    
    public void create(){
        deviceController.create(getDevice());
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

//    public DeviceController getDeviceController() {
//        return deviceController;
//    }
//
//    public void setDeviceController(DeviceController deviceController) {
//        this.deviceController = deviceController;
//    }
    
}
