/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IRepairerController;
import fr.adaming.awal.webinterface.bean.form.RepairerParameters;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@SessionScoped
public class RepairerManager implements Serializable {

    ApplicationContext springContext;

    /**
     * Creates a new instance of ClientManager
     */
    public RepairerManager() {
    }

    @PostConstruct
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
    }

    public String update() {
        FacesContext context = FacesContext.getCurrentInstance();
        RepairerParameters parameters = context.getApplication().evaluateExpressionGet(context, "#{repairerParameters}", RepairerParameters.class);

        IRepairerController controller = (IRepairerController) springContext.getBean("repairerController");
        if (controller == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal error", "Internal error"));
            return null;
        }
        
        return null;
    }

}
