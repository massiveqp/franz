package com.piclub.alpha.controller;


import com.piclub.alpha.dao.EnrollmentDao;
import com.piclub.alpha.model.Enrollment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnrollmentControllerIT {
    @Autowired
    private EnrollmentDao enrollmentDao;

    @Autowired
    private EnrollmentController controller;

    private static final String activity_ID_1 = "98765401";
    private static final String activity_ID_2 = "98765402";

    private String enrollmentId1;
    private String enrollmentId2;

    @Before
    public void setUp() {
        Enrollment enrollment = new Enrollment();
        enrollment.setActivityId(activity_ID_1);
        enrollment.setUserId("666");
        enrollment.setPayStatus(0);
        enrollment.setCheckedIn(0);
        enrollment.setEnrollStatus(1);
        enrollment.setUsername("RockLee");

        enrollmentDao.insertEnrollment(enrollment);

        enrollment.setActivityId(activity_ID_2);
        enrollmentDao.insertEnrollment(enrollment);

        List<Enrollment> enrollments = enrollmentDao.selectEnrollmentByAct(activity_ID_1);
        enrollmentId1 = enrollments.get(0).getEnrollmentId();

        enrollments = enrollmentDao.selectEnrollmentByAct(activity_ID_2);
        enrollmentId2 = enrollments.get(0).getEnrollmentId();

    }

    @After
    public void tearDown() {
        enrollmentDao.deleteEnrollment(activity_ID_2);
        enrollmentDao.deleteEnrollment(activity_ID_1);
    }

    @Test
    public void testCancelEnroll() {
        controller.cancelEnroll(enrollmentId1);

        Enrollment cancelled = enrollmentDao.selectEnrollmentById(enrollmentId1);
        assertNotNull(cancelled);
        assertEquals(0, cancelled.getEnrollStatus().intValue());
    }

    @Test
    public void testUpdatePayStatus() {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(enrollmentId1);
        enrollment.setPayStatus(1);

        controller.modifyPayStatus(enrollment);

        Enrollment updated = enrollmentDao.selectEnrollmentById(enrollmentId1);
        assertEquals(1, updated.getPayStatus().intValue());
    }

    @Test
    public void testUpdateCheckedIn() {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(enrollmentId2);
        enrollment.setCheckedIn(1);

        controller.modifyCheckedIn(enrollment);

        Enrollment updated = enrollmentDao.selectEnrollmentById(enrollmentId2);
        assertEquals(1, updated.getCheckedIn().intValue());
    }
}
