/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.pdf;

import fr.adaming.awal.controller.interfaces.IDeviceController;
import fr.adaming.awal.controller.interfaces.IDeviceRepairController;
import fr.adaming.awal.entity.Client;
import fr.adaming.awal.entity.Devicerepair;
import fr.adaming.awal.entity.Firm;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0217
 */
@ManagedBean(name = "pDFDeviceRepair")
@SessionScoped
public class PDFDeviceRepair implements Serializable {

    ApplicationContext springContext;
    private int idDeviceRepair;
    private Devicerepair devicerepair;

    public void createPDF(Devicerepair devicerepair) {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
        IDeviceRepairController deviceController = (IDeviceRepairController) springContext.getBean("deviceRepairController");
        String fileName = "DeviceRepair-"+getIdDeviceRepair();
        String title = "Shipment";
        try {
            createPDFFile(fileName,title,devicerepair);
            
        } catch (IOException ex) {
            Logger.getLogger(PDFDeviceRepair.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Devicerepair> getDevicesRepairByClient() {
        IDeviceRepairController deviceRepairController = (IDeviceRepairController) springContext.getBean("deviceRepairController");
        IDeviceController deviceController = (IDeviceController) springContext.getBean("deviceController");
        return deviceRepairController.getDevicesRepairByClient(deviceController.getById(idDeviceRepair).getClient());
    }

    public void createPDFFile(String filename,String title,Devicerepair deviceRepair) throws IOException {
        File file = File.createTempFile(filename, ".pdf");
        String filepath = file.getAbsolutePath();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "max-age=0");
        response.setHeader("Content-disposition", "attachment; "+ "filename=" + filename);

        GeneratePDFDevices.createPDF(filepath,title,deviceRepair);
        ByteArrayOutputStream baos = convertPDFToByteArrayOutputStream(filepath);
        baos.writeTo(response.getOutputStream());
        fc.responseComplete();
    }

    private static ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {

        InputStream inputStream = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            inputStream = new FileInputStream(fileName);

            byte[] buffer = new byte[1024];
            baos = new ByteArrayOutputStream();

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos;
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
