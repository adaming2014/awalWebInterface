/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import fr.adaming.awal.controller.AdminController;
import fr.adaming.awal.controller.ClientController;
import fr.adaming.awal.controller.RepairerController;
import fr.adaming.awal.controller.ResellerController;
import fr.adaming.awal.controller.UserController;
import fr.adaming.awal.entity.Admin;
import fr.adaming.awal.entity.Client;
import fr.adaming.awal.entity.Repairer;
import fr.adaming.awal.entity.Reseller;
import fr.adaming.awal.entity.User;
import fr.adaming.awal.entity.interfaces.IUser;
import fr.adaming.awal.util.RepairerUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0227
 */
@ManagedBean
@SessionScoped
public class AdminChangePermission implements Serializable {

    public static final String TYPE_ADMIN = "Admin";
    public static final String TYPE_CLIENT = "Client";
    public static final String TYPE_RESELLER = "Reseller";
    public static final String TYPE_REPAIRER = "Repairer";

    private final UserController userControlller;
    private final AdminController adminController;
    private final RepairerController repairerController;
    private final ResellerController resellerController;
    private final ClientController clientController;

    /**
     * Creates a new instance of AdminChangePermission
     */
    public AdminChangePermission() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        userControlller = (UserController) context.getBean("userController");
        adminController = (AdminController) context.getBean("adminController");
        repairerController = (RepairerController) context.getBean("repairerController");
        resellerController = (ResellerController) context.getBean("resellerController");
        clientController = (ClientController) context.getBean("clientController");
    }

    public void changePermission(AjaxBehaviorEvent event) {
        String droitChoisi = (String) ((UIInput) event.getSource()).getValue();
        IUser iuser = userControlller.getUserTypeByUserId(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user")));
        if (iuser == null) {
            System.out.println("NO USER FOUND FOR ID : " + FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user"));
            return;
        }

        if (TYPE_ADMIN.equals(droitChoisi)) {
            Admin admin = new Admin();

            if (iuser instanceof Client) {
                clientController.delete((Client) iuser);
            }
            if (iuser instanceof Repairer) {
                repairerController.delete((Repairer) iuser);
            }
            if (iuser instanceof Reseller) {
                resellerController.delete((Reseller) iuser);
            }

            admin.setUser(iuser.getUser());
            adminController.create(admin);
        }

        if (TYPE_REPAIRER.equals(droitChoisi)) {
            Repairer repairer = new Repairer();

            if (iuser instanceof Client) {
                clientController.delete((Client) iuser);
            }
            if (iuser instanceof Admin) {
                adminController.delete((Admin) iuser);
            }
            if (iuser instanceof Reseller) {
                resellerController.delete((Reseller) iuser);
            }

            repairer.setUser(iuser.getUser());
            repairer.setAvailable(RepairerUtil.NOT_AVAILABLE);
            repairerController.create(repairer);
        }

        if (TYPE_RESELLER.equals(droitChoisi)) {
            Reseller reseller = new Reseller();

            if (iuser instanceof Client) {
                clientController.delete((Client) iuser);
            }
            if (iuser instanceof Admin) {
                adminController.delete((Admin) iuser);
            }
            if (iuser instanceof Repairer) {
                repairerController.delete((Repairer) iuser);
            }

            reseller.setUser(iuser.getUser());
            resellerController.create(reseller);
        }

        if (TYPE_CLIENT.equals(droitChoisi)) {
            Client client = new Client();

            if (iuser instanceof Reseller) {
                resellerController.delete((Reseller) iuser);
            }
            if (iuser instanceof Admin) {
                adminController.delete((Admin) iuser);
            }
            if (iuser instanceof Repairer) {
                repairerController.delete((Repairer) iuser);
            }

            client.setUser(iuser.getUser());
            clientController.create(client);
        }
    }
}
