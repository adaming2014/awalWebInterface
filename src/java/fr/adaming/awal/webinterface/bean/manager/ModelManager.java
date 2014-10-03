/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IModelController;
import fr.adaming.awal.entity.Modele;
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
public class ModelManager implements Serializable{

    ApplicationContext springContext;
    private Modele modele;
    /**
     * Creates a new instance of ModelManager
     */
    public ModelManager() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
        modele = new Modele();
    }
    
    public List<Modele> getAll(){
        IModelController modelController = (IModelController) springContext.getBean("modelController");
        return modelController.getAll();
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }
    
    
}
