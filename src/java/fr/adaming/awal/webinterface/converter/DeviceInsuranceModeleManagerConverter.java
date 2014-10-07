/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.converter;

import fr.adaming.awal.entity.Deviceinsurancemodel;
import fr.adaming.awal.webinterface.bean.manager.DeviceInsuranceModeleManager;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author INTI0217
 */
@FacesConverter("deviceInsuranceModeleManagerConverter")
public class DeviceInsuranceModeleManagerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return ((DeviceInsuranceModeleManager) findBean(context, "deviceInsuranceModeleManager")).getById(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Deviceinsurancemodel) value).getIdDeviceinsurancemodel().toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T findBean(FacesContext context, String beanName) {
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }
}
