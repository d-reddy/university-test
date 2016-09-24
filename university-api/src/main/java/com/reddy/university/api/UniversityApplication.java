package com.reddy.university.api;

import com.google.inject.Guice;
import com.reddy.university.api.resources.ReportingResource;
import com.reddy.university.domain.DomainModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by deven on 9/24/2016.
 */
public class UniversityApplication extends Application<UniversityConfiguration> {
    public static void main(String[] args) throws Exception {
        try {
            new UniversityApplication().run(args);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(Bootstrap<UniversityConfiguration> bootstrap) {
    }

    @Override
    public void run(UniversityConfiguration configuration,
                    Environment environment) {

        // nothing to do yet
        InjectorProvider.setInjector(Guice.createInjector(new ConfigurationModule(configuration), new DomainModule()));

        // nothing to do yet
        environment.jersey().register(InjectorProvider.getInjector().getInstance(ReportingResource.class));
    }
}
