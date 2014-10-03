/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.util;

import javax.faces.application.FacesMessage;

/**
 *
 * @author INTI0221
 */
public class FacesMessageUtil {

    public static final FacesMessage MESSAGE_INTERNAL_ERROR = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal error", "Internal error");
    public static final FacesMessage MESSAGE_CONTROLER_NOT_FOUND = MESSAGE_INTERNAL_ERROR;
    public static final FacesMessage MESSAGE_DATABASE_ERROR = MESSAGE_INTERNAL_ERROR;
    public static final FacesMessage MESSAGE_BEAN_NOT_FOUND = MESSAGE_INTERNAL_ERROR;
}
