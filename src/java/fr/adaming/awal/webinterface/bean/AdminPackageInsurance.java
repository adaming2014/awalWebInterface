/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import fr.adaming.awal.entity.Deviceinsurancemodel;
import fr.adaming.awal.entity.Modele;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author INTI0227
 */
@ManagedBean
@SessionScoped
public class AdminPackageInsurance implements Serializable{
    Deviceinsurancemodel deviceinsurancemodel;
    Modele modele;
    private ApplicationContext context;
    //private IDeviceInsuranceModel deviceInsuranceModel;
    /**
     * Creates a new instance of AdminPackageInsurance
     */
    
    public AdminPackageInsurance() {
        
    }
    
}
