package com.reddy.university.domain;

import com.google.inject.AbstractModule;
import com.reddy.university.domain.impl.ReportRequestProcessor;

/**
 * Created by deven on 9/24/2016.
 */
public class DomainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IReportRequestProcessor.class).to(ReportRequestProcessor.class);
    }
}
