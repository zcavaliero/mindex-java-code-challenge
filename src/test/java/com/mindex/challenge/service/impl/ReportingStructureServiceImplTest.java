package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportingStructureServiceImplTest {

    @Autowired
    private ReportingStructureService reportingStructureService;

    @Test
    public void testReportingStructureRead() {

        ReportingStructure testReports = new ReportingStructure();
        testReports.setNumberOfReports(4);
        testReports.setEmployee("16a596ae-edd3-4847-99fe-c4518e82c86f");

        ReportingStructure testedReporting = reportingStructureService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertEquals(testReports.getNumberOfReports(), testedReporting.getNumberOfReports());
    }
}
