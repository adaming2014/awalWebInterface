/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.bean.manager;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author INTI0221
 */
public class GenericManager implements Serializable {

    protected ApplicationContext springContext;

    @PostConstruct
    private void init() {
        springContext = new ClassPathXmlApplicationContext("spring-config.xml");
    }

    protected <T> T getManagedBean(final FacesContext context, final String beanName, final Class<T> beanType) {
        return context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", beanType);
    }
}
