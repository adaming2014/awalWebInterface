/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IClientController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@SessionScoped
public class ClientManager extends GenericManager {

    public void update() {
    }

    public void resetFields() {
        FacesContext context = FacesContext.getCurrentInstance();

        IClientController controller = (IClientController) springContext.getBean("clientController");

        System.out.println(controller);
    }
}
