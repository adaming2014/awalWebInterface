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

        if (authManager.isClient()) {
            return "client/update.xhtml";
        }

        return null;
    }

    public String getHomePage() {
        if (authManager.isClient()) {
            return "client/client.xhtml";
        }

        if (authManager.isReseller()) {
            return "repairer/listDeviceRepair.xhtml";
        }

        if (authManager.isReseller()) {
            return "reseller/reseller.xhtml";
        }

        return "index.xhtml";
    }

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

}
