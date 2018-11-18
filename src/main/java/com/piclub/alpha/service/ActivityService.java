package com.piclub.alpha.service;

import com.piclub.alpha.dao.ActivityDao;
import com.piclub.alpha.enums.ErrorMessage;
import com.piclub.alpha.model.Activity;
import com.piclub.alpha.exception.BizException;
import com.piclub.alpha.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityDao activityDao;

    public Activity getActivity(String activityId) {
        Activity activity = activityDao.selectActivityById(activityId);

        return activity;
    }

    public List<Activity> getActivities() {
        return activityDao.selectActivities();
    }

    public Activity createActivity(Activity activity) {
        if (StringUtils.isBlank(activity.getActivityName())
        || StringUtils.isBlank(activity.getStartTime())
        || activity.getPlace() == null) throw new BizException(ErrorMessage.request_param_error);

        activity.setStatus(0);

        activityDao.insertActivity(activity);

        return activityDao.selectActivityByName(activity.getActivityName());
    }

    public void deleteActivity(String activityId) {
        if (StringUtils.isBlank(activityId)) throw new BizException(ErrorMessage.request_param_error);

        activityDao.deleteActivity(activityId);
    }

    public Activity updateActivity(Activity activity) {
        if (StringUtils.isBlank(activity.getActivityId())
        || StringUtils.isBlank(activity.getActivityName())
        || StringUtils.isBlank(activity.getStartTime())
        || null == activity.getPlace()) throw new BizException(ErrorMessage.request_param_error);

        activityDao.updateActivity(activity);

        return activityDao.selectActivityById(activity.getActivityId());
    }
}
