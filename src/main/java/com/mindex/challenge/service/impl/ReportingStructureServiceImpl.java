package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        int count = 0;
        LOG.debug("Pulling employee by id from DB");
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid employeeId");
        }
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee.getEmployeeId());
        reportingStructure.setNumberOfReports(getDirectReports(employee, count));

        return reportingStructure;


    }

    private Integer getDirectReports(Employee employee, Integer count) {
        if (employee == null || employee.getDirectReports() == null) {
            return count;
        }
        for (Employee directReports : employee.getDirectReports()) {
            LOG.debug("Pulling employee by id " + directReports.getEmployeeId());
            count = count + 1;
            Employee employee1 = employeeRepository.findByEmployeeId(directReports.getEmployeeId());
            if (employee1.getDirectReports() != null) {
                count = getDirectReports(employee1, count);
            }
        }
        return count;
    }
}
