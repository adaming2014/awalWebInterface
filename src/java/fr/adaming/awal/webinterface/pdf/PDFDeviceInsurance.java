/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.pdf;

import fr.adaming.awal.entity.Deviceinsurance;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
@ManagedBean(name = "pDFDeviceInsurance")
@SessionScoped
public class PDFDeviceInsurance implements Serializable {

    ApplicationContext springContext;

    /**
     * Creates a new instance of PDFDeviceInsurance
     */
    public PDFDeviceInsurance() {
    }

    public void createPDF(Deviceinsurance insurance) {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");

        String fileName = "Insurance-" + insurance.getId();
        String title = "Device Insurance";
        try {
            createPDFFile(fileName, title, insurance);

        } catch (IOException ex) {
            Logger.getLogger(PDFDeviceRepair.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createPDFFile(String filename, String title, Deviceinsurance insurance) throws IOException {
        File file = File.createTempFile(filename, ".pdf");
        String filepath = file.getAbsolutePath();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "max-age=0");
        response.setHeader("Content-disposition", "attachment; " + "filename=" + filename);

        GeneratePDFInsurance.createPDF(filepath, title, insurance);
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
}
