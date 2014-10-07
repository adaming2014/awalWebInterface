/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.form;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@RequestScoped
public class FirmParameters {

    private String name;
    private String phone;
    private UploadedFile logo;
    private UploadedFile theme;

    @ManagedProperty("#{addressParameters}")
    private AddressParameters address;

    /**
     * Creates a new instance of FirmParameters
     */
    public FirmParameters() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UploadedFile getLogo() {
        return logo;
    }

    public void setLogo(UploadedFile logo) {
        this.logo = logo;
    }

    public UploadedFile getTheme() {
        return theme;
    }

    public void setTheme(UploadedFile theme) {
        this.theme = theme;
    }

    public AddressParameters getAddress() {
        return address;
    }

    public void setAddress(AddressParameters address) {
        this.address = address;
    }

}
