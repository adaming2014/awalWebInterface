/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IClientController;
import fr.adaming.awal.controller.interfaces.IDeviceInsuranceController;
import fr.adaming.awal.entity.Device;
import fr.adaming.awal.entity.Deviceinsurance;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0217
 */
@ManagedBean
@SessionScoped
public class DeviceInsuranceManager implements Serializable {

    ApplicationContext springContext;
    private Deviceinsurance deviceinsurance;
    private Date beginDate;
    private Device device;
    private String description;

    /**
     * Creates a new instance of deviceInsuranceManager
     */
    public DeviceInsuranceManager() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
        deviceinsurance = new Deviceinsurance();
        device = new Device();
    }

    public List<Deviceinsurance> getAll() {
        IDeviceInsuranceController deviceInsuranceController = (IDeviceInsuranceController) springContext.getBean("deviceInsuranceController");
        IClientController clientController = (IClientController) springContext.getBean("clientController");
        FacesContext context = FacesContext.getCurrentInstance();
        AuthManager authManager = context.getApplication().evaluateExpressionGet(context, "#{authManager}", AuthManager.class);
        return deviceInsuranceController.getDevicesInsuranceByClient(clientController.getById(authManager.getClientId()));
    }

    public String add() {
        IDeviceInsuranceController deviceInsuranceController = (IDeviceInsuranceController) springContext.getBean("deviceInsuranceController");
        deviceinsurance.setBeginDate(beginDate);
        deviceinsurance.setDescription(description);
        if (!deviceInsuranceController.create(deviceinsurance)) {
            return null;
        }
        return "addDeviceInsurance";
    }

    public Deviceinsurance getDeviceinsurance() {
        return deviceinsurance;
    }

    public void setDeviceinsurance(Deviceinsurance deviceinsurance) {
        this.deviceinsurance = deviceinsurance;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
