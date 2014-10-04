/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.converter;

import fr.adaming.awal.entity.Modelpackage;
import fr.adaming.awal.webinterface.bean.manager.ModelPackageManager;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author INTI0217
 */
@FacesConverter("modelPackageConverter")
public class ModelPackageConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return ((ModelPackageManager) findBean(context, "modelPackageManager")).getById(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Modelpackage) value).getIdModelpackage().toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T findBean(FacesContext context, String beanName) {
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }
}
