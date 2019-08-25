package di.context;

import di.beans.User;
import di.business.BusinessService;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = { "di" })
class SpringContext {
}


public class LaunchJavaContext {

    private static final User DUMMY_USER = new User("dummy");

    public static Logger logger = Logger.getLogger(LaunchJavaContext.class);

    public static void main(String[] args) {
        Logger.getRootLogger().setLevel(Level.TRACE);

        ApplicationContext context = new AnnotationConfigApplicationContext(
                SpringContext.class);

        BusinessService service = context.getBean(BusinessService.class);

        logger.debug(service.calculateSum(DUMMY_USER));
    }
}
