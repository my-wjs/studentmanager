package com.studentmanager.service;

import com.studentmanager.entity.Student;
import com.studentmanager.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentService implements StudentMapper {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> queryList(Map<String, Object> map) {
        return studentMapper.queryList(map);
    }

    @Override
    public Integer queryCount(Map<String, Object> map) {
        return studentMapper.queryCount(map);
    }

    @Override
    public int deleteByForeach(List<String> username) {
        return studentMapper.deleteByForeach(username);
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public int editPassword(Student student) {
        return studentMapper.editPassword(student);
    }

    @Override
    public int stuCount() {
        return studentMapper.stuCount();
    }

    @Override
    public Map<String, Object> selectStudentInfoByUsername(String username) {
        return studentMapper.selectStudentInfoByUsername(username);
    }


    @Override
    public int editStudent(Student student) {
        return studentMapper.editStudent(student);
    }

    @Override
    public Student findByStudent(Student student) {
        return studentMapper.findByStudent(student);
    }

    @Override
    public Student findStudentByStudent(Student student) {
        return studentMapper.findStudentByStudent(student);
    }

    @Override
    public List<Student> findStudentByName(String name, int start, int pagesize) {
        return studentMapper.findStudentByName(name, start, pagesize);
    }

    @Override
    public Student findStudentById(Integer id) {
        return studentMapper.findStudentById(id);
    }

}
