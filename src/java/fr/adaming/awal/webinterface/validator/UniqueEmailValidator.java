/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.validator;

import fr.adaming.awal.controller.interfaces.IUserController;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import org.omnifaces.validator.ValueChangeValidator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0221
 */
@FacesValidator("fr.adaming.awal.webinterface.validator.UniqueEmailValidator")
public class UniqueEmailValidator extends ValueChangeValidator {

    @Override
    public void validateChangedObject(FacesContext context, UIComponent uic, Object value) {
        try (ConfigurableApplicationContext springContext = new ClassPathXmlApplicationContext("spring-config.xml")) {

            IUserController userController = (IUserController) springContext.getBean("userController");
            if (userController == null) {
                throw new RuntimeException("Bean not found");
            }

            if (userController.getByEmail((String) value) != null) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email", "This email is already used"));
            }
        }
    }
}
