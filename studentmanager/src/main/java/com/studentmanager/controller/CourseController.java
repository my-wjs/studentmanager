package com.studentmanager.controller;

import com.alibaba.fastjson.JSON;
import com.studentmanager.entity.Course;
import com.studentmanager.entity.Student;
import com.studentmanager.service.CourseService;
import com.studentmanager.utils.AjaxResult;
import com.studentmanager.utils.tableModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     *
     * @param request
     * @param response
     * @param session
     * @return
     */

    @RequestMapping("/addCourse")
    @ResponseBody
    public AjaxResult addCourse(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Integer c_no = Integer.parseInt(request.getParameter("c_no"));
        String c_name = request.getParameter("c_name");
        String c_information = request.getParameter("c_information");
        Integer c_num = Integer.parseInt(request.getParameter("c_num"));
        Course course = new Course();
        course.setC_no(c_no);
        course.setC_name(c_name);
        course.setC_information(c_information);
        course.setC_num(c_num);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = courseService.coursesInsert(course);
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
     * 分页显示课程信息
     *
     * @param limit
     * @param page
     */
    @RequestMapping("/getCourse")
    @ResponseBody
    public Object getCourse(@RequestParam("limit") String limit, @RequestParam("page") String page) {
        int lim = Integer.parseInt(limit);
        int start = (Integer.parseInt(page) - 1) * lim;
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("pagesize", lim);
        List<Course> allCourse = courseService.queryList(map);
        List<Course> c = new ArrayList<>();
        for (int i = 0; i < allCourse.size(); i++) {
            Integer c_id = allCourse.get(i).getC_id();
            Integer c_no = allCourse.get(i).getC_no();
            String c_name = allCourse.get(i).getC_name();
            String c_information = allCourse.get(i).getC_information();
            Integer c_max = allCourse.get(i).getC_max();
            Integer c_num = allCourse.get(i).getC_num();
            c.add(new Course(c_id, c_no, c_name, c_information, c_max, c_num));
        }
        int total = courseService.CourseCount();
        System.out.println(total);
        tableModel l = tableModel.data(total, c);
        return JSON.toJSON(l);
    }

    /**
     * 查询课程信息(模糊查询)
     *
     * @param c_name
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("/getCourseByName")
    @ResponseBody
    public String getStudentByName(@RequestParam("key[id]") String c_name, @RequestParam("limit") String limit, @RequestParam("page") String page) {
        int lim = Integer.parseInt(limit);
        int start = (Integer.parseInt(page) - 1) * lim;
        if (c_name.equals("")) {
            Map<String, Object> map = new HashMap<>();
            map.put("start", start);
            map.put("pagesize", lim);
            List<Course> courseList = courseService.queryList(map);
            int total = courseService.CourseCount();
            tableModel l = tableModel.data(total, courseList);
            return JSON.toJSONString(l);
        } else {
            List<Course> courseList = courseService.findCourseByName(c_name, start, lim);
            int total = courseList.size();
            tableModel l = tableModel.data(total, courseList);
            System.out.println("课程信息：" + JSON.toJSONString(l));
            return JSON.toJSONString(l);
        }

    }

    /**
     * 删除课程
     *
     * @param nums
     * @return
     */
    @RequestMapping("/deleteCourse")
    @ResponseBody
    public String deleteStu(@RequestParam("nums") String nums) {
        String datas = nums.toString();
        System.out.println(datas);
        String[] str = datas.split(",");
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < str.length; i++) {
            data.add(str[i]);
        }
        if (courseService.deleteByForeach(data) > 0) {
            return "others/success";
        } else {
            return "others/fail";
        }
    }

    /**
     * 修改课程
     *
     * @param course
     * @return
     */
    @PostMapping("/updateCourse")
    @ResponseBody
    public AjaxResult editCourse(Course course) {

        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = courseService.editCourse(course);
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
     * 选课
     *
     * @param c_id
     * @return
     */
    @RequestMapping("selectCourse")
    public String selectCourse(Integer c_id, HttpServletRequest request) {
        Integer c_num = Integer.parseInt(request.getParameter("nums"));
        Course course = new Course();
        course.setC_num(c_num);
        if (c_num == null) {
            c_num = c_num + 1;
        }
        return "";
    }
}
