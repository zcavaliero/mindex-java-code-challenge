package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Compensation create(Compensation compensation) {
        if ( compensation.getSalary() <= 0) {
            LOG.debug("Compensation salary value is incorrect [{}]", compensation.getSalary());
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Salary must be positive number");
        }
        try {
            DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT).parse(compensation.getEffectiveDate());
        } catch (DateTimeParseException e) {
            LOG.debug("Compensation effective date incorrect [{}]", compensation.getEffectiveDate());
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Date must be in yyyy-MM-dd format");
        }


        LOG.debug("Creating compensation [{}]", compensation);
        compensation.setEmployeeId(compensation.getEmployeeId());
        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation read(String id) {
        LOG.debug("Reading compensation for employee with id [{}]", id);

        Compensation compensation = compensationRepository.findByEmployeeId(id);

        if (compensation == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid employeeId");
        }

        return compensation;
    }

}
