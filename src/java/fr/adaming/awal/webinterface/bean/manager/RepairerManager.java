/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IRepairerController;
import fr.adaming.awal.entity.Repairer;
import fr.adaming.awal.entity.User;
import fr.adaming.awal.webinterface.bean.form.RepairerParameters;
import fr.adaming.awal.webinterface.bean.form.UserParameters;
import fr.adaming.awal.webinterface.util.FacesMessageUtil;
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
public class RepairerManager extends GenericManager {

    private static final String PAGE_INDEX_REDIRECT = "index_redirect";

    @ManagedProperty("#{authManager}")
    private AuthManager authManager;

    public void update() {
        FacesContext context = FacesContext.getCurrentInstance();
        RepairerParameters repairerParameters = getManagedBean(context, "repairerParameters", RepairerParameters.class);
        UserParameters userParameters = repairerParameters.getUserParameters();

        IRepairerController controller = (IRepairerController) springContext.getBean("repairerController");

        Repairer repairer = controller.getById(authManager.getRepairerId());
        if (repairer == null) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DATABASE_ERROR);
            return;
        }

        User user = repairer.getUser();

        user.setFirstname(userParameters.getFirstname());
        user.setLastname(userParameters.getLastname());
        user.setMail(userParameters.getEmail());
        user.setPassword(userParameters.getPassword());
        user.setPhone(userParameters.getPhone());

        repairer.setAvailable(repairerParameters.getAvailable());
        repairer.setFirm(repairerParameters.getFirm());
        repairer.setUser(user);

        System.out.println("FIRM : " + repairerParameters.getFirm());

        if (!controller.update(repairer)) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DATABASE_ERROR);
            return;
        }

        context.addMessage(null, FacesMessageUtil.INFO_USER_UPDATED);
    }

    public void resetFields() {
        FacesContext context = FacesContext.getCurrentInstance();
        RepairerParameters repairerParameters = getManagedBean(context, "repairerParameters", RepairerParameters.class);
        UserParameters userParameters = repairerParameters.getUserParameters();

        IRepairerController controller = (IRepairerController) springContext.getBean("repairerController");

        Repairer repairer = controller.getById(authManager.getRepairerId());
        if (repairer == null) {
            context.addMessage(null, FacesMessageUtil.MESSAGE_DATABASE_ERROR);
            return;
        }

        User user = repairer.getUser();

        userParameters.setEmail(user.getMail());
        userParameters.setFirstname(user.getFirstname());
        userParameters.setLastname(user.getLastname());
        userParameters.setPhone(user.getPhone());

        repairerParameters.setAvailable(repairer.getAvailable());
        repairerParameters.setFirm(repairer.getFirm());
    }

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

}
