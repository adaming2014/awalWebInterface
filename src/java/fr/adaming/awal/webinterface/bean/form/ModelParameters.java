/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.form;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author INTI0217
 */
@ManagedBean
@javax.faces.bean.RequestScoped
public class ModelParameters implements Serializable{

    private double weigth;
    private String dimension;
    private String brand;
    private String type;
    private String name;
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public ModelParameters() {
    }

    public String getWeigth() {
        return String.valueOf(weigth);
    }

    public void setWeigth(String weigth) {
        this.weigth = Double.parseDouble(weigth);
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
