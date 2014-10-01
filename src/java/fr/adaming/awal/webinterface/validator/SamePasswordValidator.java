/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author INTI0221
 */
@FacesValidator("fr.adaming.awal.webinterface.validator.SamePasswordValidator")
public class SamePasswordValidator implements Validator {

    /**
     * Creates a new instance of SamePasswordValidator
     */
    public SamePasswordValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password = (String) value;

        UIInput confirmComponent = (UIInput) component.getAttributes().get("passwordConfirm");
        String confirm = (String) confirmComponent.getSubmittedValue();

        if (password == null || password.isEmpty() || confirm == null || confirm.isEmpty()) {
            return; // Let required="true" do its job.
        }

        if (!password.equals(confirm)) {
            confirmComponent.setValid(false);
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password", "Passwords are not equal."));
        }
    }
}
