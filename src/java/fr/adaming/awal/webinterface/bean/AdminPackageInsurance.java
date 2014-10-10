/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import fr.adaming.awal.controller.DeviceinsurancemodelController;
import fr.adaming.awal.controller.ModelController;
import fr.adaming.awal.controller.interfaces.IDeviceinsurancemodelController;
import fr.adaming.awal.controller.interfaces.IModelController;
import fr.adaming.awal.entity.Deviceinsurancemodel;
import fr.adaming.awal.entity.Modele;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0227
 */
@ManagedBean
@SessionScoped
public class AdminPackageInsurance implements Serializable{
    
    private Deviceinsurancemodel deviceinsurancemodel;
    private Modele modele;
    private ApplicationContext context;
    private DeviceinsurancemodelController deviceInsuranceModelController;
    private ModelController modelController;

    
    
    public AdminPackageInsurance() {
                context = new ClassPathXmlApplicationContext("spring-config.xml");
                modelController = (ModelController) context.getBean("modelController");
                deviceInsuranceModelController =  (DeviceinsurancemodelController) context.getBean("deviceInsuranceModelController");
                modele = new Modele();
                deviceinsurancemodel = new Deviceinsurancemodel();
    }

    public Deviceinsurancemodel getDeviceinsurancemodel() {
        return deviceinsurancemodel;
    }

    public void setDeviceinsurancemodel(Deviceinsurancemodel deviceinsurancemodel) {
        this.deviceinsurancemodel = deviceinsurancemodel;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }
    public List<Modele> getAllModels(){
        return modelController.getAll();
    }
    public void createNewPackageInsurance(){
        modelController.create(modele);
        deviceinsurancemodel.setModele(modele);
        deviceInsuranceModelController.create(deviceinsurancemodel);
    }
    public void createNewPackageInsuranceFromExist(){
        deviceinsurancemodel.setModele(modelController.getById(modele.getIdModele()));
        deviceInsuranceModelController.create(deviceinsurancemodel);
    }
    public void createNewModel(){
        modelController.create(modele);
    }
    
    
}
