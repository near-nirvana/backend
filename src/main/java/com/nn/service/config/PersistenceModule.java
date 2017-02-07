package com.nn.service.config;

import com.google.inject.AbstractModule;

/**
 * Created by santiago.ginzburg on 2/12/16.
 */
public class PersistenceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DataInitializer.class);
    }
}
