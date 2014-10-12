/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.list;

import fr.adaming.awal.controller.interfaces.IAdminController;
import fr.adaming.awal.controller.interfaces.IClientController;
import fr.adaming.awal.controller.interfaces.IRepairerController;
import fr.adaming.awal.controller.interfaces.IResellerController;
import fr.adaming.awal.entity.interfaces.IUser;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@RequestScoped
public class UserList extends GenericList {

    private List<IUser> users;

    public List<IUser> getUsers() {
        return users;
    }

    @Override
    protected void load() {
        IClientController clientController = (IClientController) springContext.getBean("clientController");
        IRepairerController repairerController = (IRepairerController) springContext.getBean("repairerController");
        IResellerController resellerController = (IResellerController) springContext.getBean("resellerController");
        IAdminController adminController = (IAdminController) springContext.getBean("adminController");

        users = new ArrayList<>();
        users.addAll(clientController.getAll());
        users.addAll(repairerController.getAll());
        users.addAll(resellerController.getAll());
        users.addAll(adminController.getAll());
    }

}
