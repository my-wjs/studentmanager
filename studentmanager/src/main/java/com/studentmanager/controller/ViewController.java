package com.studentmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class ViewController {

    @RequestMapping("/welcome")
    public String welcome() {
        return "system/welcome";
    }

    @RequestMapping("/stuInfo_1")
    public String stuInfo_1() {
        return "system/stuInfo";
    }

    @RequestMapping("/stuInfo_2")
    public String stuInfo_2() {
        return "teacher/stuInfo";
    }

    @RequestMapping("/stuInfo")
    public String stuInfo() {
        return "student/stuInfo";
    }

    @RequestMapping("/addStudentInfo")
    public String stuAdd() {
        return "system/stuAdd";
    }

    @RequestMapping("/addTeacherInfo")
    public String addTeacher() {
        return "system/teacherAdd";
    }

    @RequestMapping("/addCourseInfo")
    public String addCourse() {
        return "course/addCourse";
    }

    @RequestMapping("/teacherInfo")
    public String teacherInfo() {
        return "teacher/teacherInfo";
    }

    @RequestMapping("/teacherInfo_1")
    public String teacherInfo_1() {
        return "system/teacherInfo";
    }

    @RequestMapping("/edit")
    public String edit() {
        return "others/edit";
    }

    @RequestMapping("/editPassword")
    public String editPassword() {
        return "others/editPassword";
    }

    @RequestMapping("/scoreInfo")
    public String scoreInfo() {
        return "course/scoreInfo";
    }

    @RequestMapping("/scoreAdd")
    public String scoreAdd() {
        return "course/addScore";
    }

    @RequestMapping("/courseInfo")
    public String courseInfo() {
        return "course/courseInfo";
    }

    @RequestMapping("/courseInfo1")
    public String courseInfo1() {
        return "student/selectCourse";
    }

    @RequestMapping("/selectCoursesInfo")
    public String selectCoursesInfo() {
        return "student/selectCourseInfo";
    }

    @RequestMapping("/selectCoursesInfo1")
    public String selectCoursesInfo1() {
        return "system/selectCourseInfo";
    }

    @RequestMapping("/selectCourseInfo")
    public String selectCourseInfo() {
        return "teacher/selectCourseInfo";
    }

    @RequestMapping("/editCourse")
    public String editCourse() {
        return "course/editCourse";
    }

    @RequestMapping("/editStudent")
    public String editStudent() {
        return "system/editStudent";
    }

    @RequestMapping("/editTeacher")
    public String editTeacher() {
        return "system/editTeacher";
    }

}
