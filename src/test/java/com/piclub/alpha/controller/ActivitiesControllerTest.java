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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivitiesControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(ActivitiesControllerTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private TestRestTemplate restTemplate;

    private String deleteId;
    private String updateId;
    private String selectId;
    private String createId;
    private String urlPrefix = "http://localhost:";

    @Before
    public void setUp() {
        deleteId = createActivity("delete");
        updateId = createActivity("update");
        selectId = createActivity("select");
    }

    private String createActivity(String name) {
        Activity activityPre = activityDao.selectActivityByName(name);
        if (activityPre != null) return activityPre.getActivityId();

        Activity activity = newActivity(name);

        activityDao.insertActivity(activity);
        Activity activityDb = activityDao.selectActivityByName(name);

        logger.info("Activity {} created!", activityDb.getActivityId());

        return activityDb.getActivityId();
    }

    private Activity newActivity(String name) {
        Activity activity = new Activity();
        activity.setActivityName(name);
        activity.setPlace(1);
        activity.setPrice(10000);
        activity.setStartTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        activity.setStatus(2);

        return activity;
    }

    @After
    public void tearDown() {
//        activityDao.deleteActivity(deleteId);
//        activityDao.deleteActivity(updateId);
//        activityDao.deleteActivity(selectId);
    }

    @Test
    public void testGetActivities() throws Exception {
        String ret = this.restTemplate.getForObject(urlPrefix + port + "/activities",
                    String.class);

        assertThat(ret).contains("select");
    }

    @Test
    public void testGetActivity() {
        String ret = this.restTemplate.getForObject(urlPrefix + port + "/activity/" + selectId, String.class);

        assertThat(ret).contains("select");
        assertThat(ret).contains(selectId);
    }

    @Test
    public void testCreateActivity() {
        String actName = "testCreate";
        Activity create = newActivity(actName);

        ResponseEntity<Activity> responseEntity = restTemplate.postForEntity(urlPrefix + port + "/activities", create, Activity.class);
        Activity created = responseEntity.getBody();

        assertNotNull(created);
        assertEquals(actName, created.getActivityName());

        createId = created.getActivityId();
        logger.info("Created activity " + created.getActivityId());

        //teardown
        activityDao.deleteActivity(createId);
    }

    @Test
    public void testUpdateActivity() {
        String updatedName = "testUpdateActivityName";
        Integer updatedStatus = 8;

        Activity update = activityDao.selectActivityById(updateId);
        assertNotNull(update);

        update.setActivityName(updatedName);
        update.setStatus(updatedStatus);
        ResponseEntity<Activity> responseEntity = restTemplate.postForEntity(urlPrefix + port + "/activity/" + updateId,
                update, Activity.class);
        Activity updated = responseEntity.getBody();

        assertNotNull(updated);
        assertEquals(updateId, updated.getActivityId());
        assertEquals(updatedName, updated.getActivityName());
        assertEquals(updatedStatus, updated.getStatus());
        //can not access milliseconds
//        assertNotEquals(update.getUpdateTime(), updated.getUpdateTime());

    }

    @Test
    public void testDeleteActivity() {
        Activity delete = activityDao.selectActivityById(deleteId);
        assertNotNull(delete);

        restTemplate.delete(urlPrefix + port + "/activity/" + deleteId);

        Activity deleted = activityDao.selectActivityById(deleteId);
        assertNull(deleted);
    }

}
