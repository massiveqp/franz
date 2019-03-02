package com.piclub.alpha.dao;

import com.piclub.alpha.model.Activity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ActivityDaoTest {
    @Autowired
    private ActivityDao dao;

    private String testActivityId;


    @Before
    public void setUp() {
        testActivityId = insertAct("testAct");
    }

    @After
    public void tearDown() {
        dao.deleteActivity(testActivityId);
    }

    @Test
    public void testGetActivities() {
        List<Activity> activities = dao.selectActivities();

        assertTrue(activities.size() > 0);
    }

    @Test
    public void testGetActivityById() {
        Activity activity = dao.selectActivityById(testActivityId);

        assertNotNull(activity);
        assertEquals("testAct", activity.getActivityName());
    }

    @Test
    public void testInsertActivity() {
        Activity activity = dao.selectActivityByName("testInsert");
        assertNull(activity);

        activity = newAct("testInsert");
        dao.insertActivity(activity);

        Activity activityDb = dao.selectActivityByName("testInsert");
        assertNotNull(activityDb);

        dao.deleteActivity(activityDb.getActivityId());
    }

    @Test
    public void testUpdateActivity() {
        Activity activity = dao.selectActivityById(testActivityId);
        assertNotNull(activity);

        activity.setActivityName("updatedName");
        activity.setStatus(2);
        activity.setStartTime("2018-10-10 11:12:14.0");
        dao.updateActivity(activity);

        Activity activityDb = dao.selectActivityById(testActivityId);
        assertEquals("updatedName", activityDb.getActivityName());
        assertEquals(2, activityDb.getStatus().intValue());
        assertEquals("2018-10-10 11:12:14", activityDb.getStartTime());
    }

    @Test
    public void testDeleteActivitiy() {
        Activity activity = dao.selectActivityById(testActivityId);
        assertNotNull(activity);

        dao.deleteActivity(testActivityId);

        Activity activityDb = dao.selectActivityById(testActivityId);
        assertNull(activityDb);

    }

    private Activity newAct (String actName) {
        Activity activity = new Activity();
        activity.setActivityName(actName);
        activity.setPlace(1);
        activity.setPrice(10000);
        activity.setStartTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        activity.setStatus(1);

        return activity;
    }

    private String insertAct(String actName) {
        Activity activity = newAct(actName);

        dao.insertActivity(activity);

        String actId = dao.selectActivityByName(actName).getActivityId();

        return actId;
    }
}
