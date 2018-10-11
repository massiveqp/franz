package com.piclub.alpha.service;

import com.piclub.alpha.dao.ActivityDao;
import com.piclub.alpha.model.Activity;
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

    public void createActivity(Activity activity) {
        //todo param check
        activityDao.insertActivity(activity);
    }

    public void deleteActivity(String activityId) {
        activityDao.deleteActivity(activityId);
    }

    public void updateActivity(Activity activity) {
        activityDao.updateActivity(activity);
    }
}
