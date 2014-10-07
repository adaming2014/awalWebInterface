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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@SessionScoped
public class AuthManager extends GenericManager {

    private IUser user;
    private static final String PAGE_INDEX = "index";
    private static final String PAGE_CLIENT = "client";
    private static final String PAGE_ADMIN = "admin";
    private static final String PAGE_SIGNIN = "signin";
    private static final String PAGE_PWD = "lostpassword";
    private static final String PAGE_DISCONNECT = "disconnect";

    /**
     * Creates a new instance of AuthManager
     */
    public AuthManager() {
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserParameters loginParameters = context.getApplication().evaluateExpressionGet(context, "#{userParameters}", UserParameters.class);

        IUserController userController = (IUserController) springContext.getBean("userController");
        if (userController == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal error", "Internal error"));
            return null;
        }

        User userTmp = userController.getByEmail(loginParameters.getEmail());
        if (userTmp == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Utilisateur inexistant", "Utilisateur inexistant"));
            return null;
        }

        if (!userTmp.getPassword().equals(loginParameters.getPassword())) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Mauvais mot de passe", "Mauvais mot de passe"));
            return null;
        }

        for (Object client : userTmp.getClients()) {
            if (((Client) client).getUser().equals(userTmp)) {
                // auth client
                this.setUser((Client) client);
                return PAGE_CLIENT;
            }
        }

        for (Object repairer : userTmp.getRepairers()) {
            if (((Repairer) repairer).getUser().equals(userTmp)) {
                // auth repairer
                this.setUser((Repairer) repairer);
                return PAGE_INDEX;
            }
        }

        for (Object reseller : userTmp.getResellers()) {
            if (((Reseller) reseller).getUser().equals(userTmp)) {
                // auth reseller
                this.setUser((Reseller) reseller);
                return PAGE_INDEX;
            }
        }
        for (Object admin : userTmp.getAdmins()) {
            if (((Admin) admin).getUser().equals(userTmp)) {
                // auth admin
                this.setUser((Admin) admin);
                return PAGE_ADMIN;
            }
        }

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal error", "Fatal error"));
        return null;

    }

    public String disconnect() {
        setUser(null);

        return PAGE_DISCONNECT;
    }

    public String lostPWD() {
        return PAGE_PWD;
    }

    public boolean isAuth() {
        return user != null;
    }

    public boolean isClient() {
        return user instanceof Client;
    }

    public boolean isRepairer() {
        return user instanceof Repairer;
    }

    public boolean isAdmin() {
        return user instanceof Admin;
    }

    public boolean isReseller() {
        return user instanceof Reseller;
    }

    public IUser getUser() {
        return user;
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public int getAdminId() {
        return ((Admin) user).getId();
    }

    public int getResellerId() {
        return ((Reseller) user).getId();
    }

    public int getRepairerId() {
        return ((Repairer) user).getId();
    }

    public int getClientId() {
        return ((Client) user).getId();
    }

    public Admin getAdmin() {
        return (Admin) user;
    }

    public Reseller getReseller() {
        return (Reseller) user;
    }

    public Repairer getRepairer() {
        return (Repairer) user;
    }

    public Client getClient() {
        return (Client) user;
    }
}
