/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import fr.adaming.awal.controller.interfaces.IClientController;
import fr.adaming.awal.entity.Client;
import fr.adaming.awal.entity.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@SessionScoped
public class SigninManager implements Serializable {
    
    private static final String PAGE_INDEX = "index";
    
    @ManagedProperty("#{authManager}")
    AuthManager authManager;
    
    ApplicationContext springContext;

    /**
     * Creates a new instance of SigninManager
     */
    public SigninManager() {
    }
    
    @PostConstruct
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
    }
    
    public String signin() {
        FacesContext context = FacesContext.getCurrentInstance();
        SigninParameters signinParameters = context.getApplication().evaluateExpressionGet(context, "#{signinParameters}", SigninParameters.class);
        
        IClientController clientController = (IClientController) springContext.getBean("clientController");
        if (clientController == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal error", "Internal error"));
            return null;
        }
        
        User user = new User();
        user.setFirstname(signinParameters.getFirstname());
        user.setLastname(signinParameters.getLastname());
        user.setMail(signinParameters.getEmail());
        user.setPassword(signinParameters.getPassword());
        
        Client client = new Client();
        client.setUser(user);
        
        if (!clientController.create(client)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal error", "Internal error"));
            return null;
        }
        
        authManager.setUser(client);
        
        return PAGE_INDEX;
    }
    
    public AuthManager getAuthManager() {
        return authManager;
    }
    
    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }
}
