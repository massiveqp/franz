package com.piclub.alpha.service;

import com.piclub.alpha.dao.EnrollmentDao;
import com.piclub.alpha.enums.ErrorMessage;
import com.piclub.alpha.exception.BizException;
import com.piclub.alpha.model.Enrollment;
import com.piclub.alpha.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentDao enrollmentDao;

    public List<Enrollment> getEnrollmentsByAct(String activityId) {
        return enrollmentDao.selectEnrollmentByAct(activityId);
    }

    public Enrollment enroll(Enrollment enrollment) {
        if (StringUtils.isBlank(enrollment.getActivityId())
            || StringUtils.isBlank(enrollment.getUsername())) {
            throw new BizException(ErrorMessage.request_param_error);
        }

        enrollment.setPayStatus(0);
        enrollment.setCheckedIn(0);
        enrollment.setUserId("666");
        enrollment.setEnrollStatus(1);

        // todo merge into a saveAndReturnId operation
        enrollmentDao.insertEnrollment(enrollment);
        return enrollmentDao.findEnrollByActAndUser(enrollment.getActivityId(), enrollment.getUsername());
    }

    public void cancelEnroll(String enrollmentId) {
        if (StringUtils.isBlank(enrollmentId)) {
            throw new BizException(ErrorMessage.request_param_error);
        }

//        Enrollment enrollment = new Enrollment();
//        enrollment.setEnrollmentId(enrollmentId);
//        enrollment.setEnrollStatus(0);

        enrollmentDao.deleteEnrollmentById(enrollmentId);
    }

    public void updatePaymentStatus(String enrollmentId, Integer payStatus) {
        if (StringUtils.isBlank(enrollmentId) || null == payStatus) {
            throw new BizException(ErrorMessage.request_param_error);
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(enrollmentId);
        enrollment.setPayStatus(payStatus);

        enrollmentDao.updatePayStatus(enrollment);
    }

    public void updateCheckedInStatus(String enrollmentId, Integer checkedIn){
        if (StringUtils.isBlank(enrollmentId) || null == checkedIn) {
            throw new BizException(ErrorMessage.request_param_error);
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(enrollmentId);
        enrollment.setCheckedIn(checkedIn);

        enrollmentDao.updateCheckedIn(enrollment);
    }
}
