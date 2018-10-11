package com.piclub.alpha.controller;

import com.piclub.alpha.model.Activity;
import com.piclub.alpha.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/activities")
public class ActivitiesController {
    private static Logger logger = LoggerFactory.getLogger(ActivitiesController.class);

    @Autowired
    private ActivityService activityService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Activity> getActivities() {
        logger.info("Starting getActivities");
        return activityService.getActivities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createActivity(@RequestBody Activity activity) {
        logger.info("Starting createActivity");
        activityService.createActivity(activity);
    }
}
