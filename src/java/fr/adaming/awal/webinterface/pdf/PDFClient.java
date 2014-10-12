/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.pdf;

import fr.adaming.awal.entity.Client;
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
@ManagedBean(name = "pDFClient")
@SessionScoped
public class PDFClient implements Serializable {

    ApplicationContext springContext;

    /**
     * Creates a new instance of PDFClient
     */
    public PDFClient() {
    }

    public void createPDF(Client client) {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
        
        String fileName = "Client-" + client.getId();
        String title = "Client piece of information";
        try {
            createPDFFile(fileName, title, client);

        } catch (IOException ex) {
            Logger.getLogger(PDFDeviceRepair.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createPDFFile(String filename, String title, Client client) throws IOException {
        File file = File.createTempFile(filename, ".pdf");
        String filepath = file.getAbsolutePath();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "max-age=0");
        response.setHeader("Content-disposition", "attachment; " + "filename=" + filename);

        GeneratePDFClient.createPDF(filepath, title, client);
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
