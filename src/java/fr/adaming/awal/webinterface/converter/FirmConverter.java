package fr.adaming.awal.webinterface.converter;

import fr.adaming.awal.controller.interfaces.IRepairerController;
import fr.adaming.awal.entity.Firm;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@FacesConverter("firmConverter")
public class FirmConverter implements Converter {

    private ApplicationContext springContext;

    @PostConstruct
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                IRepairerController controller = (IRepairerController) springContext.getBean("repairerController");
                if (controller == null) {
                    return null;
                }

                return controller.getById(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((Firm) object).getIdFirm());
        } else {
            return null;
        }
    }
}
