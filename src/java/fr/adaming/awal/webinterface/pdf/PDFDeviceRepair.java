/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.pdf;

import fr.adaming.awal.controller.interfaces.IDeviceController;
import fr.adaming.awal.controller.interfaces.IDeviceRepairController;
import fr.adaming.awal.entity.Devicerepair;
import java.io.Serializable;
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
public class PDFDeviceRepair implements Serializable {

    ApplicationContext springContext;
    private int idDeviceRepair;
    private Devicerepair devicerepair;

    public void createPDF() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
    }

    private List<Devicerepair> getDevicesRepairByClient() {
        IDeviceRepairController deviceRepairController = (IDeviceRepairController) springContext.getBean("deviceRepairController");
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        return deviceRepairController.getDevicesRepairByClient(deviceController.getById(idDeviceRepair).getClient());
    }

    public int getIdDeviceRepair() {
        return idDeviceRepair;
    }

    public String setIdDeviceRepair(int idDeviceRepair) {
        this.idDeviceRepair = idDeviceRepair;
        return "pdf";
    }

    public Devicerepair getDevicerepair() {
        return devicerepair;
    }

    public void setDevicerepair(Devicerepair devicerepair) {
        this.devicerepair = devicerepair;
    }

}
