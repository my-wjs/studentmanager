package com.studentmanager.mapper;

import com.studentmanager.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> queryList(Map<String, Object> map);

    Integer queryCount(Map<String, Object> map);

    int deleteByForeach(List<String> username);

    int addStudent(Student student);

    int editPassword(Student student);

    int stuCount();

    Map<String, Object> selectStudentInfoByUsername(@Param("username") String username);

    int editStudent(Student student);

    Student findByStudent(Student student);

    Student findStudentByStudent(Student student);

    List<Student> findStudentByName(String name, int start, int pagesize);

    Student findStudentById(Integer id);
}
