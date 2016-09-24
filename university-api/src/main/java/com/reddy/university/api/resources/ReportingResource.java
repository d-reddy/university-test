package com.reddy.university.api.resources;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.reddy.university.domain.IReportRequestProcessor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by deven on 9/24/2016.
 */
@Path("/report")
public class ReportingResource {
    private IReportRequestProcessor processor;
    private String inputFile;

    @Inject
    public ReportingResource(IReportRequestProcessor processor, @Named("inputFile") String inputFile) {
        this.inputFile = inputFile;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String index() {
        //this.processor.
        return "<h1>It Works!!!</h1> ";
    }

}
