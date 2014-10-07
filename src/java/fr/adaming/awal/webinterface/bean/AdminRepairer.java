/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import fr.adaming.awal.controller.RepairerController;
import fr.adaming.awal.entity.Repairer;
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
public class AdminRepairer implements Serializable{

    private RepairerController repairerController;
    private ApplicationContext context;
    /**
     * Creates a new instance of AdminRepairer
     */
    public AdminRepairer() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        repairerController = (RepairerController) context.getBean("repairerController");
    }
    public List<Repairer> getAllRepairers(){
        return repairerController.getAll();
       
    }
    
    
}
