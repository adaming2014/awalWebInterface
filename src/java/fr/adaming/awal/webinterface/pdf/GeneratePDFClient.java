/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import fr.adaming.awal.entity.Client;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author INTI0217
 */
public class GeneratePDFClient {

    private static Font TIME_ROMAN
            = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font TIME_ROMAN_SMALL
            = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    /**
     * @param args
     */
    public static Document createPDF(String file, String title, Client client) throws IOException {

        Document document = null;

        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            addMetaData(document);
//            addlogoHeader(document, client);
            addTitlePage(document, title);
            addInfoAddress(document, client);
            document.close();

        } catch (FileNotFoundException | DocumentException e) {

            e.printStackTrace();
        }
        return document;

    }

    private static void addMetaData(Document document) {
        document.addTitle("Generate PDF report");
        document.addSubject("Generate PDF report");
        document.addAuthor("Awal");
        document.addCreator("Awal");
    }

    private static void addlogoHeader(Document document, Client client) throws DocumentException, IOException {

            Image image1 = Image.getInstance("client.png");
            document.add(image1);
           
    }

    private static void addTitlePage(Document document, String title) throws DocumentException {

        Paragraph preface = new Paragraph();
        creteEmptyLine(preface, 1);
        preface.add(new Paragraph(title, TIME_ROMAN));

        creteEmptyLine(preface, 1);
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("MM/dd/yyyy");
        preface.add(new Paragraph(title
                + " created on " + simpleDateFormat
                .format(new Date()), TIME_ROMAN_SMALL));
        document.add(preface);
    }

    private static void addInfoAddress(Document document, Client client) throws DocumentException {

        Paragraph preface = new Paragraph();
        creteEmptyLine(preface, 1);
        preface.add(new Paragraph("Address Firm : \n" + client.getFirm().getName()
                + "\n" + client.getFirm().getAddress().getCity()
                + "\n" + client.getFirm().getAddress().getStreet()
                + "\n" + client.getFirm().getAddress().getPostcode()
                + "\n", TIME_ROMAN_SMALL));
        creteEmptyLine(preface, 1);
        preface.add(new Paragraph("Address Client : \n" + client.getUser().getFirstname()
                + " " + client.getUser().getLastname()
                + "\n" + client.getAddress().getCity()
                + "\n" + client.getAddress().getStreet()
                + "\n" + client.getAddress().getPostcode()
                + "\n Client piece of information : \n Mail :" + client.getUser().getMail()
                + "\n Password : " + client.getUser().getPassword()
                + "\n", TIME_ROMAN_SMALL));
        creteEmptyLine(preface, 1);
        document.add(preface);
    }

    private static void creteEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
