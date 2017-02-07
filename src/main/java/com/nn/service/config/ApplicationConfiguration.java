package com.nn.service.config;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

/**
 * Created by santiagoginzburg on 07/02/2017.
 *
 * java … -Darchaius.configurationSource.defaultFileName=customName.properties
 */
public class ApplicationConfiguration {


    static {
        System.setProperty("archaius.configurationSource.defaultFileName", "application.properties");
    }

    public String getStringProperty(String key, String defaultValue) {
        final DynamicStringProperty property = DynamicPropertyFactory.getInstance().getStringProperty(key,
                defaultValue);
        return property.get();
    }
}
