package com.reddy.university.api.resources;

import com.google.inject.Inject;
import com.reddy.university.domain.IReportService;
import com.reddy.university.domain.models.UniversityClassSummary;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
@Path("/report")
public class ReportingResource {
    private IReportService reportService;

    @Inject
    public ReportingResource(IReportService reportService) {
        this.reportService = reportService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UniversityClassSummary> index() {
        List<UniversityClassSummary> summaries = new ArrayList<>();
        try {
            summaries = this.reportService.getClassesTaught();
        }catch(Exception e){

        }

        return summaries;
    }

}
