/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@SessionScoped
public class SigninManager implements Serializable {

    /**
     * Creates a new instance of SigninManager
     */
    public SigninManager() {
    }

    public void signin() {
        FacesContext context = FacesContext.getCurrentInstance();
        SigninParameters signinParameters = context.getApplication().evaluateExpressionGet(context, "#{signinParameters}", SigninParameters.class);
        
        System.out.println(signinParameters);
    }
}
