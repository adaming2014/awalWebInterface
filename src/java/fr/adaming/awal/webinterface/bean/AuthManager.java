/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import fr.adaming.awal.entity.Admin;
import fr.adaming.awal.entity.Client;
import fr.adaming.awal.entity.Repairer;
import fr.adaming.awal.entity.Reseller;
import fr.adaming.awal.entity.interfaces.IUser;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@SessionScoped
public class AuthManager implements Serializable {

    private IUser user;

    /**
     * Creates a new instance of AuthManager
     */
    public AuthManager() {
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
