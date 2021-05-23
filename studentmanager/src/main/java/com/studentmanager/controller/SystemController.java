package com.studentmanager.controller;

import com.studentmanager.entity.Admin;
import com.studentmanager.entity.Student;
import com.studentmanager.entity.Teacher;
import com.studentmanager.service.AdminService;
import com.studentmanager.service.StudentService;
import com.studentmanager.service.TeacherService;
import com.studentmanager.utils.AjaxResult;
import com.studentmanager.utils.Const;
import com.studentmanager.utils.CpachaUtil;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 跳转登录界面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录表单提交 校验
     *
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult submitLogin(String username, String password, String captcha, String type,
                                  HttpSession session) {
        session.setAttribute("username", username);
        AjaxResult ajaxResult = new AjaxResult();

        switch (type) {
            case "1": { //管理员
                Admin admin = new Admin();
                admin.setPassword(password);
                admin.setUsername(username);
                Admin ad = adminService.findByAdmin(admin);
                if (StringUtils.isEmpty(ad)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("管理员登陆成功");
                ajaxResult.setType("1");
                session.setAttribute(Const.ADMIN, ad);
                session.setAttribute(Const.USERTYPE, "1");
                break;
            }
            case "2": {//学生
                Student student = new Student();
                student.setPassword(password);
                student.setUsername(username);
                Student st = studentService.findByStudent(student);
                if (StringUtils.isEmpty(st)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("学生登陆成功");
                ajaxResult.setType("2");
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.STUDENT, st);
                session.setAttribute(Const.USERTYPE, "2");
                break;
            }
            case "3": {//教师
                Teacher teacher = new Teacher();
                teacher.setPassword(password);
                teacher.setUsername(username);
                Teacher tr = teacherService.findByTeacher(teacher);
                if (StringUtils.isEmpty(tr)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("教师登陆成功");
                ajaxResult.setType("3");
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.TEACHER, tr);
                session.setAttribute(Const.USERTYPE, "3");
                break;
            }
        }
        return ajaxResult;
    }

    /**
     * 显示 验证码
     *
     * @param request
     * @param response
     * @param vl
     * @param w
     * @param h
     */
    @GetMapping("/checkCode")
    public void generateCpacha(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "vl", defaultValue = "4", required = false) Integer vl,
                               @RequestParam(value = "w", defaultValue = "110", required = false) Integer w,
                               @RequestParam(value = "h", defaultValue = "39", required = false) Integer h) {
        CpachaUtil cpachaUtil = new CpachaUtil(vl, w, h);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute(Const.CODE, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转后台主页
     *
     * @return
     */
    @GetMapping("/index")
    public String adminIndex() {
        return "system/index";
    }

    @GetMapping("/studentIndex")
    public String studentIndex() {
        return "student/studentIndex";
    }

    @GetMapping("/teacherIndex")
    public String teacherIndex() {
        return "teacher/teacherIndex";
    }


    /**
     * 登出
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/login";
    }

    /**
     * 修改密码
     *
     * @param password
     * @param newPassword
     * @param session
     * @return
     */
    @PostMapping("/editPassword")
    @ResponseBody
    public AjaxResult editPassword(String password, String newPassword, HttpSession session) {
        AjaxResult ajaxResult = new AjaxResult();
        String usertype = (String) session.getAttribute(Const.USERTYPE);
        if (usertype.equals("1")) {
            //管理员
            Admin admin = (Admin) session.getAttribute(Const.ADMIN);
            if (!password.equals(admin.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            admin.setPassword(newPassword);
            try {
                int count = adminService.editPassword(admin);
                if (count > 0) {
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                } else {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        System.out.println(password);
        System.out.println(newPassword);
        if (usertype.equals("2")) {
            //学生
            Student student = (Student) session.getAttribute(Const.STUDENT);
            if (!password.equals(student.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            student.setPassword(newPassword);
            try {
                int count = studentService.editPassword(student);
                if (count > 0) {
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                } else {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        if (usertype.equals("3")) {
            //教师
            Teacher teacher = (Teacher) session.getAttribute(Const.TEACHER);
            if (!password.equals(teacher.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            teacher.setPassword(newPassword);
            try {
                int count = teacherService.editPassword(teacher);
                if (count > 0) {
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                } else {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        return ajaxResult;
    }
}
