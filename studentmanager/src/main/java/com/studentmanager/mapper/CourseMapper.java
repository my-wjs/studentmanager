package com.studentmanager.mapper;

import com.studentmanager.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseMapper {
    // 课程添加
    int coursesInsert(Course course);

    // 分页显示所有课程信息
    List<Course> queryList(Map<String, Object> map);

    int CourseCount();

    //模糊查询
    List<Course> findCourseByName(String c_name, int start, int pagesize);

    // 删除课程
    int deleteByForeach(List<String> c_name);

    int editCourse(Course course);

    //选课
    int addSelectedCourse(Course course);

}
