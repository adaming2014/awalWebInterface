/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.form;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author INTI0221
 */
@ManagedBean
@RequestScoped
public class ClientParameters {

    @ManagedProperty("#{addressParameters}")
    private AddressParameters address;

    @ManagedProperty("#{userParameters}")
    private UserParameters user;

    private String numbercard;

    /**
     * Creates a new instance of ClientParameters
     */
    public ClientParameters() {
    }

    public AddressParameters getAddress() {
        return address;
    }

    public void setAddress(AddressParameters address) {
        this.address = address;
    }

    public String getNumbercard() {
        return numbercard;
    }

    public void setNumbercard(String numbercard) {
        this.numbercard = numbercard;
    }

    public UserParameters getUser() {
        return user;
    }

    public void setUser(UserParameters user) {
        this.user = user;
    }

}
