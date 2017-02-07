package com.nn.service.config;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by santiagoginzburg on 07/02/2017.
 */
public class ApplicationConfigurationTest {
    private ApplicationConfiguration appConfig = new ApplicationConfiguration();

    @Test
    public void shouldRetrieveThePropertyByKey() {
        String property = appConfig.getStringProperty("hibernate.connection.username", "leaf");

        assertThat(property, is("root"));
    }

    @Test
    public void shouldRetrieveDefaultValueWhenKeyIsNotPresent() {
        String property = appConfig.getStringProperty("hibernate.connection.user", "leaf");

        assertThat(property, is("leaf"));
    }
}
