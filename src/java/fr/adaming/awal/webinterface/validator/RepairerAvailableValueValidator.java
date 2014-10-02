/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.validator;

import fr.adaming.awal.util.RepairerUtil;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import org.omnifaces.validator.ValueChangeValidator;

/**
 *
 * @author INTI0221
 */
@FacesValidator("fr.adaming.awal.webinterface.validator.RepairerAvailableValueValidator")
public class RepairerAvailableValueValidator extends ValueChangeValidator {

    /**
     * Creates a new instance of SamePasswordValidator
     */
    public RepairerAvailableValueValidator() {
    }

    @Override
    public void validateChangedObject(FacesContext context, UIComponent component, Object value) {
        String available = (String) value;

        if (!RepairerUtil.AVAILABLE.equals(available) && !RepairerUtil.NOT_AVAILABLE.equals(available)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Available", "Incorect value."));
        }
    }
}
