/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.ClientController;
import fr.adaming.awal.controller.interfaces.IDeviceRepairController;
import fr.adaming.awal.entity.Devicerepair;
import fr.adaming.awal.entity.Modelpackage;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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

    /**
     * Creates a new instance of DeviceRepairManager
     */
    public DeviceRepairManager() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
        deviceRepair = new Devicerepair();
    }

    public String add() {
        IDeviceRepairController deviceController = (IDeviceRepairController) springContext.getBean("deviceRepairController");
//        deviceRepair.setState("CREATE");
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

}
