/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author INTI0221
 */
@ManagedBean(name = "clientManager1")
@RequestScoped
public class ClientManager {

    /**
     * Creates a new instance of ClientManager
     */
    public ClientManager() {
    }

    public void update() {
        throw new RuntimeException("Not supported yet");
    }

}
