/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import fr.adaming.awal.webinterface.bean.manager.AuthManager;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@SessionScoped
public class router implements Serializable {

    @ManagedProperty("#{authManager}")
    AuthManager authManager;

    public String getAccountUpdatePage() {
        if (!authManager.isAuth()) {
            return "auth/login.xhtml";
        }

        if (authManager.isRepairer()) {
            return "repairer/update.xhtml";
        }
        
        return null;
    }

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

}
