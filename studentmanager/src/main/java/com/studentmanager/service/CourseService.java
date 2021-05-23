package com.studentmanager.service;

import com.studentmanager.entity.Course;
import com.studentmanager.entity.Teacher;
import com.studentmanager.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseService implements CourseMapper {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public int coursesInsert(Course course) {
        return courseMapper.coursesInsert(course);
    }

    @Override
    public List<Course> queryList(Map<String, Object> map) {
        return courseMapper.queryList(map);
    }

    @Override
    public int CourseCount() {
        return courseMapper.CourseCount();
    }

    @Override
    public List<Course> findCourseByName(String c_name, int start, int pagesize) {
        return courseMapper.findCourseByName(c_name, start, pagesize);
    }

    @Override
    public int deleteByForeach(List<String> c_name) {
        return courseMapper.deleteByForeach(c_name);
    }

    @Override
    public int editCourse(Course course) {
        return courseMapper.editCourse(course);
    }

    @Override
    public int addSelectedCourse(Course course) {
        return 0;
    }


}
