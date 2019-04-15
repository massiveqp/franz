package com.piclub.alpha.dao;

import com.piclub.alpha.model.Activity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ActivityDao {
    @Results(
        id = "activityMap", value = {
            @Result(column = "activity_id", property = "activityId"),
            @Result(column = "activity_name", property = "activityName"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "people_limit", property = "peopleLimit"),
            @Result(column = "pic_url", property = "picUrl"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "half_price", property = "halfPrice")
    })
    @Select("select * from activity order by activity_id desc")
    List<Activity> selectActivities();

    @Select("select * from activity where activity_id = #{activityId}")
    @ResultMap("activityMap")
    Activity selectActivityById(String activityId);

    @Select("select * from activity where activity_name = #{activityName}")
    @ResultMap("activityMap")
    Activity selectActivityByName(String activityName);

    @Insert("insert into activity (activity_name, place, price, start_time, " +
            "end_time, people_limit, memo, pic_url, status, half_price) " +
            "values (#{activityName}, #{place}, #{price}, #{startTime}, " +
            "#{endTime}, #{peopleLimit}, #{memo}, #{picUrl}, #{status}, #{halfPrice})")
    void insertActivity(Activity activity);

    @Update("update activity set activity_name = #{activityName}, place = #{place}," +
            "price = #{price}, start_time = #{startTime}, end_time = #{endTime}," +
            "people_limit = #{peopleLimit}, memo = #{memo}, pic_url = #{picUrl}, " +
            "status = #{status}, half_price = #{halfPrice} " +
            "where activity_id = #{activityId}")
    void updateActivity(Activity activity);

    @Delete("delete from activity where activity_id = #{activityId}")
    void deleteActivity(String activityId);
}
