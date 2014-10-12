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
import fr.adaming.awal.webinterface.util.FacesMessageUtil;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author INTI0221
 */
@Named
@SessionScoped
public class AuthManager extends GenericManager {

    private static final String PAGE_HOME = "pretty:home";
    private static final String PAGE_REGISTER = "pretty:authRegister";
    private static final String PAGE_LOGIN = "pretty:authLogin";

    private IUser user;

    /**
     * Creates a new instance of AuthManager
     */
    public AuthManager() {
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserParameters loginParameters = context.getApplication().evaluateExpressionGet(context, "#{userParameters}", UserParameters.class);

        IUserController userController = (IUserController) springContext.getBean("userController");

        User userTmp = userController.getByEmail(loginParameters.getEmail());
        if (userTmp == null || !userTmp.getPassword().equals(loginParameters.getPassword())) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_INVALID_ID);
            return PAGE_LOGIN;
        }

        IUser iUser = userController.getUserTypeByUserId(userTmp.getId());
        if (iUser == null) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_INTERNAL_ERROR);
            return PAGE_LOGIN;
        }

        this.setUser(iUser);

        return PAGE_HOME;
    }

    public String logout() {
        setUser(null);

        return PAGE_HOME;
    }

    public String register() {
        return PAGE_REGISTER;
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

    public Admin getUserAdmin() {
        return (Admin) user;
    }

    public Reseller getUserReseller() {
        return (Reseller) user;
    }

    public Repairer getUserRepairer() {
        return (Repairer) user;
    }

    public Client getUserClient() {
        return (Client) user;
    }
}
