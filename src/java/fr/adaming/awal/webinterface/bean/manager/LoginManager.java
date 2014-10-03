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
    AuthManager authManager;

    ApplicationContext springContext;

    public LoginManager() {
    }

    @PostConstruct
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserParameters loginParameters = context.getApplication().evaluateExpressionGet(context, "#{userParameters}", UserParameters.class);

        IUserController userController = (IUserController)springContext.getBean("userController");
        if (userController == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal error", "Internal error"));
            return null;
        }

        User user = userController.getByEmail(loginParameters.getEmail());
        if (user == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Utilisateur inexistant", "Utilisateur inexistant"));
            return null;
        }

        if (!user.getPassword().equals(loginParameters.getPassword())) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Mauvais mot de passe", "Mauvais mot de passe"));
            return null;
        }

        for (Object client : user.getClients()) {
            if (((Client) client).getUser().equals(user)) {
                // auth client
                return PAGE_INDEX;
            }
        }
        for (Object repairer : user.getRepairers()) {
            if (((Repairer) repairer).getUser().equals(user)) {
                // auth repairer
                return PAGE_INDEX;
            }
        }
        for (Object reseller : user.getResellers()) {
            if (((Reseller) reseller).getUser().equals(user)) {
                // auth reseller
                return PAGE_INDEX;
            }
        }
        for (Object admin : user.getAdmins()) {
            if (((Admin) admin).getUser().equals(user)) {
                // auth admin
                return PAGE_INDEX;
            }
        }
        
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal error", "Fatal error"));
        return null;
                

    }

    public String signup() {
        return PAGE_SIGNIN;
    }

    public String lostPWD() {
        return PAGE_PWD;
    }
}
