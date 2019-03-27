package com.piclub.alpha.controller;

import com.piclub.alpha.model.Enrollment;
import com.piclub.alpha.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/enrollments")
public class EnrollmentsController {
    private static Logger logger = LoggerFactory.getLogger(EnrollmentsController.class);

    @Autowired
    private EnrollmentService enrollmentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Enrollment> getEnrollmentsByActId(
            @RequestParam(name = "actId") String actId,
            @RequestParam(name = "includeCancelled", required = false) boolean includeCancelled) {
        logger.info(String.format("get Enrollments by actId %s", actId));

        return enrollmentService.getEnrollmentsByAct(actId);
    }

    @PostMapping
    public ResponseEntity<Object> doEnroll(@Valid @RequestBody Enrollment enrollment) {
        logger.info("doEnroll...");

        Enrollment savedEnroll = enrollmentService.enroll(enrollment);

//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(savedEnroll.getEnrollmentId())
//                .toUri();

        logger.info(String.format("Successfully enrolled, ID: %s", savedEnroll.getEnrollmentId()));
        return ResponseEntity.created(null).build();
    }
}
