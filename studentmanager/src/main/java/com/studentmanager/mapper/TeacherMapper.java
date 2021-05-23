package com.studentmanager.mapper;

import com.studentmanager.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeacherMapper {
    List<Teacher> queryList(Map<String, Object> map);

    int deleteByForeach(List<String> username);

    int tCount();

    Integer queryCount(Map<String, Object> map);

    int deleteTeacher(List<Integer> ids);

    int addTeacher(Teacher teacher);

    int editPassword(Teacher teacher);

    Teacher findById(Integer tid);

    int editTeacher(Teacher teacher);

    Teacher findByTeacher(Teacher teacher);

    List<Teacher> findTeacherByName(String name, int start, int pagesize);

}
