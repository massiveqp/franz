package com.piclub.alpha.service;

import com.piclub.alpha.dao.EnrollmentDao;
import com.piclub.alpha.enums.ErrorMessage;
import com.piclub.alpha.exception.BizException;
import com.piclub.alpha.model.Enrollment;
import com.piclub.alpha.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
CREATE TABLE IF NOT EXISTS `piclub`.`enrollment` (
  `enrollment_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `pay_status` TINYINT NOT NULL COMMENT '0: not paid\n1: paid',
  `checked_in` TINYINT NOT NULL COMMENT '0: not checked\n1: checked',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `activity_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`enrollment_id`))
ENGINE = InnoDB;

 */
@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentDao enrollmentDao;

    public List<Enrollment> getEnrollmentsByAct(String activityId) {
        return enrollmentDao.selectEnrollmentByAct(activityId);
    }

    public void enroll(Enrollment enrollment) {
        if (StringUtils.isBlank(enrollment.getActivityId())
            || StringUtils.isBlank(enrollment.getUsername())) {
            throw new BizException(ErrorMessage.request_param_error);
        }

        enrollment.setPayStatus(0);
        enrollment.setCheckedIn(0);
        enrollment.setUserId("666");
        enrollment.setEnrollStatus(1);

        enrollmentDao.insertEnrollment(enrollment);
    }

    public void cancelEnroll(String enrollmentId) {
        if (StringUtils.isBlank(enrollmentId)) {
            throw new BizException(ErrorMessage.request_param_error);
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(enrollmentId);
        enrollment.setEnrollStatus(0);

        enrollmentDao.updateEnrollStatus(enrollment);
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
