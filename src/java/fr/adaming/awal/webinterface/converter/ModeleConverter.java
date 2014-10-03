/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.converter;

import fr.adaming.awal.entity.Modele;
import fr.adaming.awal.webinterface.bean.manager.ModelManager;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author INTI0217
 */
@FacesConverter("modeleConverter")
public class ModeleConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("sdfsdfsdf");
        Modele modele = ((ModelManager) findBean(context, "modelManager")).getById(Integer.valueOf(value));
        System.out.println("modele name : "+modele.getName());
        return modele;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String model = ((Modele) value).getIdModele().toString();
        System.out.println("model :"+model);
        return model;
    }

    @SuppressWarnings("unchecked")
    public static <T> T findBean(FacesContext context, String beanName) {
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }
}
