package com.java.task11.controller.persistance;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class HibernateUtil {
    private static Logger log = Logger.getLogger(HibernateUtil.class);

    static {
        PropertyConfigurator.configure("src/log4j.properties");
    }

    private static SessionFactory sessionFactory = null;

    private static final Configuration configuration = new Configuration();

    static {
        try {
            // Creates the session factory from hibernate.cfg.xml
            configuration.configure();
            sessionFactory = configuration
                    .buildSessionFactory(new ServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .buildServiceRegistry());
        } catch (Exception e) {
            log.error(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
