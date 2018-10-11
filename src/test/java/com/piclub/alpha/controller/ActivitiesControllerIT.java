package com.piclub.alpha.controller;

import com.piclub.alpha.dao.ActivityDao;
import com.piclub.alpha.model.Activity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ActivitiesControllerIT {
    private static final Logger logger = LoggerFactory.getLogger(ActivitiesControllerIT.class);

    @LocalServerPort
    private int port;

    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private TestRestTemplate restTemplate;

    private String insertId;
    private String updateId;
    private String selectId;
    private String urlPrefix = "http://localhost:";

    @Before
    public void setUp() {
        insertId = createActivity("insert");
        updateId = createActivity("update");
        createActivity("delete");
        selectId = createActivity("select");
    }

    private String createActivity(String name) {
        Activity activityPre = activityDao.selectActivityByName(name);
        if (activityPre != null) return activityPre.getActivityId();

        Activity activity = new Activity();
        activity.setActivityName(name);
        activity.setPlace(1);
        activity.setPrice(10000);
        activity.setStartTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        activity.setStatus(1);

        activityDao.insertActivity(activity);
        Activity activityDb = activityDao.selectActivityByName(name);

        logger.info("Activity {} created!", activityDb.getActivityId());

        return activityDb.getActivityId();
    }

    @After
    public void tearDown() {
        activityDao.deleteActivity(insertId);
        activityDao.deleteActivity(updateId);
        activityDao.deleteActivity(selectId);
    }

    @Test
    public void testGetActivities() throws Exception {
        String ret = this.restTemplate.getForObject(urlPrefix + port + "/activities",
                    String.class);

        assertThat(ret).contains("insert");
    }

    @Test
    public void testGetActivity() {
        String ret = this.restTemplate.getForObject(urlPrefix + port+ "/activity/" + selectId, String.class);

        assertThat(ret).contains("select");
    }

    @Test
    public void testCreateActivity() {

    }

    @Test
    public void testUpdateActivity() {

    }

    @Test
    public void testDeleteActivity() {

    }

}
