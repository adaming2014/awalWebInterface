/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IDeviceController;
import fr.adaming.awal.controller.interfaces.IDeviceinsurancemodelController;
import fr.adaming.awal.entity.Deviceinsurancemodel;
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
public class DeviceInsuranceModeleManager implements Serializable{

    ApplicationContext springContext;
    private int iddevice;
    /**
     * Creates a new instance of DeviceInsuranceModeleManager
     */
    public DeviceInsuranceModeleManager() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
    }
    
    public List<Deviceinsurancemodel> getDeviceInsuranceModelsByModel(){
        IDeviceinsurancemodelController controller = (IDeviceinsurancemodelController) springContext.getBean("deviceController");
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        return controller.getDeviceInsuranceModelsByModel(deviceController.getById(iddevice).getModele());
    }
    
    public int getIddevice() {
        return iddevice;
    }

    public String setIddevice(int iddevice) {
        this.iddevice = iddevice;
        return "addInsurance";
    }
}
