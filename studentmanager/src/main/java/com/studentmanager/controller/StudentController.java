package com.studentmanager.controller;

import com.alibaba.fastjson.JSON;
import com.studentmanager.entity.Course;
import com.studentmanager.entity.Student;
import com.studentmanager.entity.Teacher;
import com.studentmanager.service.StudentService;
import com.studentmanager.utils.AjaxResult;
import com.studentmanager.utils.Const;
import com.studentmanager.utils.tableModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 添加学生信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addStudent")
    @ResponseBody
    public AjaxResult addStudent(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String username = request.getParameter("username");
        String s_name = request.getParameter("s_name");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String tCollege = request.getParameter("tCollege");
        String tProfessional = request.getParameter("tProfessional");
        Integer classId = Integer.parseInt(request.getParameter("classId"));

        Student student = new Student();

        student.setUsername(username);
        student.setS_name(s_name);
        student.setPassword(password);
        student.setSex(sex);
        student.setsCollege(tCollege);
        student.setsProfessional(tProfessional);
        student.setClassId(classId);

        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = studentService.addStudent(student);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("添加成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }
        return ajaxResult;
    }

    /**
     * 获取学生信息
     *
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("/getStudent")
    @ResponseBody
    public Object getStudent(@RequestParam("limit") String limit, @RequestParam("page") String page) {
        int lim = Integer.parseInt(limit);
        int start = (Integer.parseInt(page) - 1) * lim;
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("pagesize", lim);
        List<Student> allStu = studentService.queryList(map);
        List<Student> stu = new ArrayList<>();
        for (int i = 0; i < allStu.size(); i++) {
            Integer id = allStu.get(i).getId();
            String username = allStu.get(i).getUsername();
            String s_name = allStu.get(i).getS_name();
            String sex = allStu.get(i).getSex();
            String sCollege = allStu.get(i).getsCollege();
            String sProgessional = allStu.get(i).getsProfessional();
            Integer classId = allStu.get(i).getClassId();
            stu.add(new Student(id, username, s_name, sex, sCollege, sProgessional, classId));
        }
        int total = studentService.stuCount();
        System.out.println(total);
        tableModel l = tableModel.data(total, stu);
        return JSON.toJSON(l);
    }

    /**
     * 学生个人信息
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping("/getStudentInfo")
    @ResponseBody
    public AjaxResult getStudentInfo(@Param("username") String username, HttpServletRequest request, HttpServletResponse response,
                                     HttpSession session) {
        AjaxResult ajaxResult = new AjaxResult();
        studentService.selectStudentInfoByUsername(username);
        return ajaxResult;
    }

    /**
     * 查询学生信息(模糊查询)
     *
     * @param name
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("/getStudentByName")
    @ResponseBody
    public String getStudentByName(@RequestParam("key[id]") String name, @RequestParam("limit") String limit, @RequestParam("page") String page) {
        int lim = Integer.parseInt(limit);
        int start = (Integer.parseInt(page) - 1) * lim;
        if (name.equals("")) {
            Map<String, Object> map = new HashMap<>();
            map.put("start", start);
            map.put("pagesize", lim);
            List<Student> studentList = studentService.queryList(map);
            int total = studentService.stuCount();
            tableModel l = tableModel.data(total, studentList);
            return JSON.toJSONString(l);
        } else {
            List<Student> studentList = studentService.findStudentByName(name, start, lim);
            int total = studentList.size();
            tableModel l = tableModel.data(total, studentList);
            System.out.println("学生信息：" + JSON.toJSONString(l));
            return JSON.toJSONString(l);
        }

    }

    /**
     * 删除学生
     *
     * @param nums
     * @return
     */
    @RequestMapping("/deleteStu")
    public String deleteStu(@RequestParam("nums") String nums) {
        String datas = nums.toString();
        System.out.println(datas);
        String[] str = datas.split(",");
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < str.length; i++) {
            data.add(str[i]);
        }
        System.out.println(data.toString());
        if (studentService.deleteByForeach(data) > 0) {
            return "others/success";
        } else {
            return "others/fail";
        }
    }

    /**
     * 修改学生信息
     *
     * @param student
     * @return
     */
    @PostMapping("/updateStudent")
    @ResponseBody
    public AjaxResult updateStudent(Student student) {

        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = studentService.editStudent(student);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("修改成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }

    /**
     * 学生选课
     */
    @RequestMapping("/chooseCourse")
    public String chooseCourse() {

        return "";
    }

}
