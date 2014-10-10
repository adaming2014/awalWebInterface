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
    public static final FacesMessage MESSAGE_DATABASE_ERROR = MESSAGE_INTERNAL_ERROR;
    public static final FacesMessage MESSAGE_BEAN_NOT_FOUND = MESSAGE_INTERNAL_ERROR;

    public static final FacesMessage MESSAGE_FIRM_NOT_FOUND = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Firm : Firm not found", "Firm : Incorrect value of firm id");
    public static final FacesMessage MESSAGE_DEVICE_NOT_CREATE = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Device : Device not create", "Device : Incorrect value of Device id");
    public static final FacesMessage MESSAGE_MODEL_NOT_CREATE = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Model : Device not model", "Model : Incorrect value of Model id");
    public static final FacesMessage MESSAGE_DEVICE_REPAIR_ALREADY_EXIST = new FacesMessage(FacesMessage.SEVERITY_ERROR, "DeviceRepair : DeviceRepair already exist", "DeviceRepair : DeviceRepair already exist");

    public static final FacesMessage INFO_FIRM_UPDATED = new FacesMessage(FacesMessage.SEVERITY_INFO, "Firm updated !", "The firm has been correctly updated.");
    public static final FacesMessage INFO_USER_UPDATED = new FacesMessage(FacesMessage.SEVERITY_INFO, "User updated !", "The user has been correctly updated.");
    public static final FacesMessage INFO_DEVICE_CREATE = new FacesMessage(FacesMessage.SEVERITY_INFO, "Device create !", "The Device has been correctly create.");
}
