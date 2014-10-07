/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import fr.adaming.awal.controller.interfaces.IDeviceInsuranceController;
import fr.adaming.awal.entity.Deviceinsurance;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0217
 */
@ManagedBean
@SessionScoped
public class deviceInsuranceManager implements Serializable{

    ApplicationContext springContext;
    private Deviceinsurance deviceinsurance;
    private Date beginDate;
    /**
     * Creates a new instance of deviceInsuranceManager
     */
    public deviceInsuranceManager() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
        deviceinsurance = new Deviceinsurance();
    }
    
    public List<Deviceinsurance> getAll(){
        IDeviceInsuranceController deviceInsuranceController = (IDeviceInsuranceController) springContext.getBean("deviceInsuranceController");
        return deviceInsuranceController.getAll();
    }
    
    public String add(){
        return null;
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
    
}