/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IDeviceController;
import fr.adaming.awal.controller.interfaces.IModelPackageController;
import fr.adaming.awal.entity.Device;
import fr.adaming.awal.entity.Modelpackage;
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
public class ModelPackageManager implements Serializable{

    ApplicationContext springContext;
    private int iddevice;
    
    public ModelPackageManager() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
    }

    public List<Modelpackage> getAll() {
        IModelPackageController modelController = (IModelPackageController) springContext.getBean("modelPackageController");
        return modelController.getAll();
    }

    public List<Modelpackage> getPackagesByDevice() {
        IModelPackageController modelController = (IModelPackageController) springContext.getBean("modelPackageController");
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        return modelController.getPackagesByModel(deviceController.getById(iddevice).getModele());
    }

    public Device getDevice(){
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        return deviceController.getById(iddevice);
    }
    
    public int getIddevice() {
        return iddevice;
    }

    public String setIddevice(int iddevice) {
        this.iddevice = iddevice;
        return "addPackage";
    }

    public Modelpackage getById(int id){
        IModelPackageController modelController = (IModelPackageController) springContext.getBean("modelPackageController");
        return modelController.getById(id);
    }
}
