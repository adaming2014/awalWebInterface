/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import fr.adaming.awal.controller.ModelController;
import fr.adaming.awal.controller.ModelPackageController;
import fr.adaming.awal.controller.interfaces.IModelController;
import fr.adaming.awal.controller.interfaces.IModelPackageController;
import fr.adaming.awal.entity.Modele;
import fr.adaming.awal.entity.Modelpackage;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author INTI0227
 */
@ManagedBean
@SessionScoped
public class AdminPackageDeviceMB implements Serializable{

    private Modele deviceModel;
    
    private Modelpackage modelPackage;
    private ApplicationContext context;
    private ModelPackageController modelPAckageController;
    private ModelController modelController;
    /**
     * Creates a new instance of AdminManagedBean
     */
    public AdminPackageDeviceMB() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        modelPAckageController = (ModelPackageController) context.getBean("modelPackageController");
        modelController = (ModelController) context.getBean("modelController");
        deviceModel = new Modele();
        modelPackage = new Modelpackage();
    }

    public Modele getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(Modele deviceModel) {
        this.deviceModel = deviceModel;
    }

    public Modelpackage getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(Modelpackage modelPackage) {
        this.modelPackage = modelPackage;
    }
    public List<Modele> getAllModels(){
        return modelController.getAll();
    }




    
    public void createNewPackageDevice(){
        
        modelController.create(deviceModel);
        modelPackage.setModele(deviceModel);
        modelPAckageController.create(modelPackage);
        
    }
    public void createNewPackageDeviceFromExist(){
        System.out.println("model id = " + deviceModel.getIdModele());
        modelPackage.setModele(modelController.getById(deviceModel.getIdModele()));
        modelPAckageController.create(modelPackage);
    }

    
    
    
}
