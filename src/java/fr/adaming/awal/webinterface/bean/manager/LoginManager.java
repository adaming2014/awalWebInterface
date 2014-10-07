/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IUserController;
import fr.adaming.awal.entity.Admin;
import fr.adaming.awal.entity.Client;
import fr.adaming.awal.entity.Repairer;
import fr.adaming.awal.entity.Reseller;
import fr.adaming.awal.entity.User;
import fr.adaming.awal.entity.interfaces.IUser;
import fr.adaming.awal.webinterface.bean.form.UserParameters;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0219
 */
@ManagedBean
@SessionScoped
public class LoginManager implements Serializable {

    private static final String PAGE_INDEX = "index";
    private static final String PAGE_SIGNIN = "signin";
    private static final String PAGE_PWD = "lostpassword";
    private static final String PAGE_DISCONNECT = "disconnect";

    AuthManager authManager;

    ApplicationContext springContext;

    public LoginManager() {
    }

    @PostConstruct
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
    }

   
}
