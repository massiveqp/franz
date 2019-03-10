package com.piclub.alpha.controller;

import com.piclub.alpha.model.Enrollment;
import com.piclub.alpha.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/enrollment/{enrollmentId}")
public class EnrollmentController {
    private static Logger logger = LoggerFactory.getLogger(EnrollmentController.class);

    @Autowired
    private EnrollmentService enrollmentService;

    @DeleteMapping
    public void cancelEnroll(@PathVariable String enrollmentId) {
        logger.info("cancelEnroll...");

        enrollmentService.cancelEnroll(enrollmentId);
    }

    @PostMapping(value = "/payStatus")
    public void modifyPayStatus(@RequestBody Enrollment enrollment) {
        logger.info("update pay status");

        enrollmentService.updatePaymentStatus(enrollment.getEnrollmentId(), enrollment.getPayStatus());
    }

    @RequestMapping(value = "/checkedIn", method = RequestMethod.POST)
    public void modifyCheckedIn(@RequestBody Enrollment enrollment) {
        logger.info("update checked in...");

        enrollmentService.updateCheckedInStatus(enrollment.getEnrollmentId(), enrollment.getCheckedIn());
    }
}
