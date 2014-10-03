/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import fr.adaming.awal.controller.UserController;
import fr.adaming.awal.entity.User;
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
    private User u ;
    /**
     * Creates a new instance of AdminChangePermission
     */
    public AdminChangePermission() {
        u = new User();
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        userControlller = (UserController) context.getBean("userController");
    }
    public List<String> getAllUsersFirsName(){
        List<String> usersLogins = new ArrayList<>();
        for(User user : userControlller.getAll()){
            usersLogins.add(user.getFirstname());
        }
        return usersLogins;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    
}
