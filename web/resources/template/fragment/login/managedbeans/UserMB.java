/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.template.fragment.login.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author INTI0219
 */
@ManagedBean
@SessionScoped

public class UserMB {
    
    private String firstname;
    private String lastname;
    private String mail;
    private String password;
    private String phone;

    public UserMB() {
    }

    public UserMB(String firstname, String lastname, String mail, String password, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
