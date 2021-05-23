package com.studentmanager.controller;

import com.alibaba.fastjson.JSON;
import com.studentmanager.entity.Student;
import com.studentmanager.entity.Teacher;
import com.studentmanager.service.TeacherService;
import com.studentmanager.utils.AjaxResult;
import com.studentmanager.utils.tableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 添加教师信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addTeacher")
    @ResponseBody
    public AjaxResult addStudent(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String username = request.getParameter("username");
        String t_name = request.getParameter("t_name");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String tCollege = request.getParameter("tCollege");
        String tProfessional = request.getParameter("tProfessional");
        Integer classId = Integer.parseInt(request.getParameter("classId"));
        Teacher teacher = new Teacher();
        teacher.setUsername(username);
        teacher.setT_name(t_name);
        teacher.setPassword(password);
        teacher.setSex(sex);
        teacher.settCollege(tCollege);
        teacher.settProfessional(tProfessional);
        teacher.setClassId(classId);

        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = teacherService.addTeacher(teacher);
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
     * 获取教师信息
     *
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("/getTeacher")
    @ResponseBody
    public Object getStudent(@RequestParam("limit") String limit, @RequestParam("page") String page) {
        int lim = Integer.parseInt(limit);
        int start = (Integer.parseInt(page) - 1) * lim;
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("pagesize", lim);
        List<Teacher> allTeacher = teacherService.queryList(map);
        List<Teacher> t = new ArrayList<>();
        for (int i = 0; i < allTeacher.size(); i++) {
            Integer id = allTeacher.get(i).getId();
            String username = allTeacher.get(i).getUsername();
            String t_name = allTeacher.get(i).getT_name();
            String sex = allTeacher.get(i).getSex();
            String tCollege = allTeacher.get(i).gettCollege();
            String tProfessional = allTeacher.get(i).gettProfessional();
            Integer classId = allTeacher.get(i).getClassId();
            t.add(new Teacher(id, username, t_name, sex, tCollege, tProfessional, classId));
        }
        int total = teacherService.tCount();
        System.out.println(total);
        tableModel l = tableModel.data(total, t);
        return JSON.toJSON(l);
    }

    /**
     * 查询教师信息
     *
     * @param name
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("/getTeacherByName")
    @ResponseBody
    public String getTeacherByName(@RequestParam("key[id]") String name, @RequestParam("limit") String limit, @RequestParam("page") String page) {
        int lim = Integer.parseInt(limit);
        int start = (Integer.parseInt(page) - 1) * lim;
        if (name.equals("")) {
            Map<String, Object> map = new HashMap<>();
            map.put("start", start);
            map.put("pagesize", lim);
            List<Teacher> teacherList = teacherService.queryList(map);
            int total = teacherService.tCount();
            tableModel l = tableModel.data(total, teacherList);
            return JSON.toJSONString(l);
        } else {
            List<Teacher> teacherList = teacherService.findTeacherByName(name, start, lim);
            int total = teacherList.size();
            tableModel l = tableModel.data(total, teacherList);
            System.out.println("学生信息：" + JSON.toJSONString(l));
            return JSON.toJSONString(l);
        }

    }

    /**
     * 删除教师
     *
     * @param nums
     * @return
     */
    @RequestMapping("/deleteTeacher")
    public String deleteStu(@RequestParam("nums") String nums) {
        String datas = nums.toString();
        System.out.println(datas);
        String[] str = datas.split(",");
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < str.length; i++) {
            data.add(str[i]);
        }
        System.out.println(data.toString());
        if (teacherService.deleteByForeach(data) > 0) {
            return "others/success";
        } else {
            return "others/fail";
        }
    }
}
