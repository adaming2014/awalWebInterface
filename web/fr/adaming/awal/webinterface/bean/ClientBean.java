/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author INTI0217
 */
@Named(value = "clientBean")
@SessionScoped
public class ClientBean implements Serializable {

    /**
     * Creates a new instance of ClientBean
     */
    public ClientBean() {
    }
    
}
