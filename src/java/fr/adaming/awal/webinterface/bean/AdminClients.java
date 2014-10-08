/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import fr.adaming.awal.controller.ClientController;
import fr.adaming.awal.entity.Client;
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
public class AdminClients implements Serializable{
    
    private ApplicationContext context;
    private ClientController clientController;
    /**
     * Creates a new instance of AdminClients
     */
    public AdminClients() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        clientController = (ClientController) context.getBean("clientController");
    }
    public List<Client>getAllClients(){
        return clientController.getAll();
    }
    
}
