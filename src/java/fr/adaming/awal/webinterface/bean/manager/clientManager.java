/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@SessionScoped
public class clientManager implements Serializable {

    /**
     * Creates a new instance of clientManager
     */
    public clientManager() {
    }

    public void update() {
        throw new RuntimeException("Not implemented");
    }
}
