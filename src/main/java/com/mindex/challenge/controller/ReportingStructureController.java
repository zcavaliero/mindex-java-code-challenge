package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructure.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    @GetMapping("/reporting/{id}")
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Received request for reporting structure for id [{}]", id);

        return reportingStructureService.read(id);
    }
}
