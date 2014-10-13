/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean;

import fr.adaming.awal.controller.DeviceRepairController;
import fr.adaming.awal.entity.Devicerepair;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0227
 */
@ManagedBean
@SessionScoped
public class AdminStatisticDevice implements Serializable {

    private LineChartModel areaModel;
    private ApplicationContext context;
    private DeviceRepairController devicerepairController;

    public AdminStatisticDevice() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        devicerepairController = (DeviceRepairController) context.getBean("deviceRepairController");
    }

    private List<Devicerepair> getAllDeviceReparation() {
        return devicerepairController.getAll();
    }

    public LineChartModel getAreaModel() {
        createAreaModel();
        return areaModel;
    }

    private void createAreaModel() {
        areaModel = new LineChartModel();
        LineChartSeries nbreDeviceLine = new LineChartSeries();
        LineChartSeries coutTotalLine = new LineChartSeries();

        int nbrdeviceInyear2014 = 0;
        int nbrdeviceInyear2013 = 0;
        int nbrdeviceInyear2012 = 0;
        int nbrdeviceInyear2011 = 0;

        LocalDate local2014 = LocalDate.parse("2014-01-01");
        LocalDate local2013 = LocalDate.parse("2013-01-01");
        LocalDate local2012 = LocalDate.parse("2012-01-01");
        LocalDate local2011 = LocalDate.parse("2011-01-01");

        //System.out.println("date : "+date2014);
        for (Devicerepair devicerepair : getAllDeviceReparation()) {

            LocalDate creationDate = ((java.sql.Date) devicerepair.getDateCreation()).toLocalDate();

            if (creationDate.getYear() == local2014.getYear()) {
                nbrdeviceInyear2014++;
            }
            if (creationDate.getYear() == local2013.getYear()) {
                nbrdeviceInyear2013++;
            }
            if (creationDate.getYear() == local2012.getYear()) {
                nbrdeviceInyear2012++;
            }
            if (creationDate.getYear() == local2011.getYear()) {
                nbrdeviceInyear2011++;
            }

        }

        System.out.println("Nombre de réparations" + nbrdeviceInyear2014);
        nbreDeviceLine.set("2011", nbrdeviceInyear2011);
        nbreDeviceLine.set("2012", nbrdeviceInyear2012);
        nbreDeviceLine.set("2013", nbrdeviceInyear2013);
        nbreDeviceLine.set("2014", nbrdeviceInyear2014);
        areaModel.addSeries(nbreDeviceLine);

        areaModel.setTitle("Nombre de réparation");
        areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);
        areaModel.setAnimate(true);

        Axis xAxis = new CategoryAxis("Annés");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Nombre de réparations");
        yAxis.setMin(0);
        yAxis.setMax(10);

    }
}
