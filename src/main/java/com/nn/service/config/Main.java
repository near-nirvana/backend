package com.nn.service.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.nn.service.repo.*;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
public class Main extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        Properties properties = new Properties();

        try {
            properties.load(Main.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JpaPersistModule jpaModule = new JpaPersistModule("testDB");
        jpaModule.properties(properties);

        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {

                bind(ShowcaseDao.class).to(ShowcaseImplDao.class);
                bind(DepartmentDao.class).to(DepartmentDaoImpl.class);
                bind(EmployeeDao.class).to(EmployeeDaoImpl.class);
                install(jpaModule);

                filter("/*").through(PersistFilter.class);

                ResourceConfig rc = new PackagesResourceConfig( "com.nn.service.resources" );

                for ( Class<?> resource : rc.getClasses() ) {
                    bind( resource );
                }

                Map<String, String> initParams = new HashMap<String, String>();
                initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
                serve( "/services/*" ).with(GuiceContainer.class, initParams);
            }

        });
    }
}
