/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.ClientController;
import fr.adaming.awal.controller.interfaces.IDeviceRepairController;
import fr.adaming.awal.controller.interfaces.IRepairerController;
import fr.adaming.awal.entity.Client;
import fr.adaming.awal.entity.Device;
import fr.adaming.awal.entity.Devicerepair;
import fr.adaming.awal.entity.Modelpackage;
import fr.adaming.awal.entity.Repairer;
import fr.adaming.awal.util.RepairerUtil;
import java.io.Serializable;
import java.util.Date;
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
public class DeviceRepairManager implements Serializable {

    ApplicationContext springContext;
    private Modelpackage modelPackage;
    private Devicerepair deviceRepair;
    private Device device;

    /**
     * Creates a new instance of DeviceRepairManager
     */
    public DeviceRepairManager() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
        deviceRepair = new Devicerepair();
    }

    public String add() {
        Repairer repairer = null;
        IDeviceRepairController deviceController = (IDeviceRepairController) springContext.getBean("deviceRepairController");
        IRepairerController repairerController = (IRepairerController) springContext.getBean("repairerController");
        ClientController clientController = (ClientController) springContext.getBean("clientController");
        Client client = clientController.getClientByMail("bian.loic@gmail.com");
        for (Devicerepair devicerepair : deviceController.getAll()) {
            if ((devicerepair.getDevice().getIdDevice().equals(device.getIdDevice())) && (devicerepair.getModelpackage().equals(modelPackage))) {
                return "addDeviceRepair";
            }
        }
        for (Repairer repairer1 : repairerController.getAll()) {
            
            if (repairer1.getAvailable().equals(RepairerUtil.AVAILABLE)) {
                if (repairer1.getFirm().getAddress().getPostcode().equals(client.getAddress().getPostcode())) {
                    repairer = repairer1;
                    
                }
            }
        }
        deviceRepair.setRepairer(repairer);
        deviceRepair.setDevice(device);
        deviceRepair.setState("CREATE");
        deviceRepair.setModelpackage(modelPackage);
        deviceRepair.setDateCreation(new Date());
        deviceRepair.setPrice(Integer.valueOf(modelPackage.getPrice()));
        if (!deviceController.create(deviceRepair)) {
            return null;
        }
        
        return "addDeviceRepair";
    }

    public List<Devicerepair> getDevicesRepairByClient() {
        IDeviceRepairController deviceController = (IDeviceRepairController) springContext.getBean("deviceRepairController");
        ClientController clientController = (ClientController) springContext.getBean("clientController");
        return deviceController.getDevicesRepairByClient(clientController.getClientByMail("bian.loic@gmail.com"));
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

}
