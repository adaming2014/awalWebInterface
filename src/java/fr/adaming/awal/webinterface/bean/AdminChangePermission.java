/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import fr.adaming.awal.controller.AdminController;
import fr.adaming.awal.controller.ClientController;
import fr.adaming.awal.controller.RepairerController;
import fr.adaming.awal.controller.ResellerController;
import fr.adaming.awal.controller.UserController;
import fr.adaming.awal.entity.Admin;
import fr.adaming.awal.entity.Client;
import fr.adaming.awal.entity.Repairer;
import fr.adaming.awal.entity.Reseller;
import fr.adaming.awal.entity.User;
import fr.adaming.awal.util.RepairerUtil;
import java.io.Serializable;
import java.util.ArrayList;
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
public class AdminChangePermission implements Serializable{
    private ApplicationContext context;
    private UserController userControlller;
    private AdminController adminController;
    private RepairerController repairerController;
    private ResellerController resellerController;
    private ClientController clientController;
    private User u;
    private String droitChoisi = "";

    /**
     * Creates a new instance of AdminChangePermission
     */
    public AdminChangePermission() {
        
        
        u = new User();
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        userControlller = (UserController) context.getBean("userController");
        adminController = (AdminController) context.getBean("adminController");
        repairerController = (RepairerController) context.getBean("repairerController");
        resellerController = (ResellerController) context.getBean("resellerController");
        clientController = (ClientController) context.getBean("clientController");
    }

    public List<User> getAllUsers(){
        return userControlller.getAll();
    }


  

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public String getDroitChoisi() {
        return droitChoisi;
    }

    public void setDroitChoisi(String droitChoisi) {
        this.droitChoisi = droitChoisi;
    }
    public void changePermission(){
        
        if(droitChoisi.equals("Administrateur")){
            Admin admin = new Admin();
            User userTmp = userControlller.getById(u.getIdUser());
            userTmp.setIdUser(null);
            admin.setUser(userTmp);
            userControlller.delete(u);
            userControlller.create(userTmp);
            adminController.create(admin);
            //adminController.update(admin);
            
        }
        if(droitChoisi.equals("Réparateur")){
            Repairer repairer = new Repairer();
            User userTmp = userControlller.getById(u.getIdUser());
            userTmp.setIdUser(null);
            repairer.setUser(userTmp);
            userControlller.delete(u);
            userControlller.create(userTmp);
            repairer.setAvailable(RepairerUtil.NOT_AVAILABLE);
            repairerController.create(repairer);
            
        }
        if(droitChoisi.equals("Revendeur")){
            Reseller reseller = new Reseller();
            User userTmp = userControlller.getById(u.getIdUser());
            userTmp.setIdUser(null);
            reseller.setUser(userTmp);
            userControlller.delete(u);
            userControlller.create(userTmp);
            resellerController.create(reseller);
        }
         if(droitChoisi.equals("Client")){
             Client client = new Client();
             User userTmp = userControlller.getById(u.getIdUser());
             userTmp.setIdUser(null);
             client.setUser(userTmp);
            userControlller.delete(u);
            userControlller.create(userTmp);
            clientController.create(client);
         }
        
        
    }
    
    
}
