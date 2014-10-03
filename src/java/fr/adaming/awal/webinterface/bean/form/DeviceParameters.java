/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.form;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author INTI0217
 */
@ManagedBean
@RequestScoped
public class DeviceParameters implements Serializable {

    private String description;
    /**
     * Creates a new instance of DeviceParameters
     */
    public DeviceParameters() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    
}
