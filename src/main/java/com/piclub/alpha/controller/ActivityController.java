package com.piclub.alpha.controller;

import com.piclub.alpha.model.Activity;
import com.piclub.alpha.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/activity/{activityId}")
public class ActivityController {
    private static Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    @RequestMapping(method = RequestMethod.GET)
    public Activity getActivity(@PathVariable String activityId) {
        logger.info("Starting getActivity");
        return activityService.getActivity(activityId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Activity modifyActivity(@RequestBody Activity activity,
                               @PathVariable String activityId) {
        logger.info("Starting modifyActivity");
        return activityService.updateActivity(activity);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActivity(@PathVariable String activityId) {
        logger.info("Starting deleteActivity");
        activityService.deleteActivity(activityId);
    }
}
