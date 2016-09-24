package com.reddy.university.api;

import com.google.inject.name.Names;
import com.google.inject.AbstractModule;
/**
 * Created by deven on 9/24/2016.
 */
public class ConfigurationModule extends AbstractModule {
    UniversityConfiguration config;

    public ConfigurationModule(UniversityConfiguration config){
        this.config = config;
    }

    @Override
    protected void configure(){
        bind(UniversityConfiguration.class).toInstance(this.config);
        bind(String.class).annotatedWith(Names.named("sourceDir")).toInstance(this.config.getInputFile());
    }
}
