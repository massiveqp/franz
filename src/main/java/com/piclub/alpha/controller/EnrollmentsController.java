package com.piclub.alpha.controller;

import com.piclub.alpha.model.Enrollment;
import com.piclub.alpha.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/enrollments")
public class EnrollmentsController {
    private static Logger logger = LoggerFactory.getLogger(EnrollmentsController.class);

    @Autowired
    private EnrollmentService enrollmentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Enrollment> getEnrollments(@RequestBody Enrollment enrollment) {
        logger.info("get Enrollments by act...");

        String activityId = enrollment.getActivityId();

        return enrollmentService.getEnrollmentsByAct(activityId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void doEnroll(@RequestBody Enrollment enrollment) {
        logger.info("doEnroll...");

        enrollmentService.enroll(enrollment);
    }
}
