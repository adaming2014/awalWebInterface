/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fr.adaming.awal.entity.Deviceinsurance;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author INTI0217
 */
public class GeneratePDFInsurance {

    private static Font TIME_ROMAN
            = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font TIME_ROMAN_SMALL
            = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    /**
     * @param args
     */
    public static Document createPDF(String file, String title, Deviceinsurance insurance) {

        Document document = null;

        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            addMetaData(document);

            addTitlePage(document, title);
            addInfoAddress(document, insurance);
            createTable(document, insurance);
            addFooter(document, insurance);
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

    private static void addInfoAddress(Document document, Deviceinsurance insurance) throws DocumentException {

        Paragraph preface = new Paragraph();
        creteEmptyLine(preface, 1);
        if (insurance.getDevice().getClient().getFirm() == null) {
            preface.add(new Paragraph("Awal Insurance : \n"
                    + "\n SANTEC"
                    + "\n 162, Route du theven coz"
                    + "\n 29250"
                    + "\n mail : awal@gmail.com"
                    + "\n", TIME_ROMAN_SMALL));
        } else {
            preface.add(new Paragraph("Address Firm : \n" + insurance.getDevice().getClient().getFirm().getName()
                    + "\n" + insurance.getDevice().getClient().getFirm().getAddress().getCity()
                    + "\n" + insurance.getDevice().getClient().getFirm().getAddress().getStreet()
                    + "\n" + insurance.getDevice().getClient().getFirm().getAddress().getPostcode()
                    + "\n", TIME_ROMAN_SMALL));
        }

        creteEmptyLine(preface, 1);
        preface.add(new Paragraph("Address Client : \n" + insurance.getDevice().getClient().getUser().getFirstname()
                + " " + insurance.getDevice().getClient().getUser().getLastname()
                + "\n" + insurance.getDevice().getClient().getAddress().getCity()
                + "\n" + insurance.getDevice().getClient().getAddress().getStreet()
                + "\n" + insurance.getDevice().getClient().getAddress().getPostcode()
                + "\n", TIME_ROMAN_SMALL));
        creteEmptyLine(preface, 1);
        document.add(preface);
    }

    private static void addFooter(Document document, Deviceinsurance insurance) throws DocumentException {

        Paragraph preface = new Paragraph();
        creteEmptyLine(preface, 1);
        preface.add(new Paragraph("Client must to pay : \n" + insurance.getDeviceinsurancemodel().getPrice()
                + " €\n", TIME_ROMAN_SMALL));
        creteEmptyLine(preface, 1);
        document.add(preface);
    }

    private static void creteEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static void createTable(Document document, Deviceinsurance insurance) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        creteEmptyLine(paragraph, 2);
        paragraph.add(new Paragraph(" Description Shipment : \n"));
        document.add(paragraph);
        PdfPTable table = new PdfPTable(5);

        PdfPCell c1 = new PdfPCell(new Phrase("Insurance"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Modele"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Duration"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Begin Date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("MM/dd/yyyy");
        for (int i = 0; i < 1; i++) {
            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(insurance.getDeviceinsurancemodel().getName());
            table.addCell(insurance.getDeviceinsurancemodel().getModele().getName());
            table.addCell(String.valueOf(insurance.getDeviceinsurancemodel().getPrice()) + " € ");
            table.addCell(String.valueOf(insurance.getDeviceinsurancemodel().getDuration())+ " month ");
            table.addCell(simpleDateFormat.format(insurance.getBeginDate()));
        }

        document.add(table);
    }
}
