package com.studentmanager.service;

import com.studentmanager.entity.Teacher;
import com.studentmanager.mapper.TeacherMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherService implements TeacherMapper {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> queryList(Map<String, Object> map) {
        return teacherMapper.queryList(map);
    }

    @Override
    public int deleteByForeach(List<String> username) {
        return teacherMapper.deleteByForeach(username);
    }

    @Override
    public int tCount() {
        return teacherMapper.tCount();
    }

    @Override
    public Integer queryCount(Map<String, Object> map) {
        return teacherMapper.queryCount(map);
    }

    @Override
    public int deleteTeacher(List<Integer> ids) {
        return teacherMapper.deleteTeacher(ids);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    @Override
    public int editPassword(Teacher teacher) {
        return teacherMapper.editPassword(teacher);
    }

    @Override
    public Teacher findById(Integer id) {
        return teacherMapper.findById(id);
    }

    @Override
    public int editTeacher(Teacher teacher) {
        return teacherMapper.editTeacher(teacher);
    }

    @Override
    public Teacher findByTeacher(Teacher teacher) {
        return teacherMapper.findByTeacher(teacher);
    }

    @Override
    public List<Teacher> findTeacherByName(String name, int start, int pagesize) {
        return teacherMapper.findTeacherByName(name, start, pagesize);
    }


}
