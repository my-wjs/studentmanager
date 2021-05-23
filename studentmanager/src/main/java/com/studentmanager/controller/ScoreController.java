package com.studentmanager.controller;

import com.alibaba.fastjson.JSON;
import com.studentmanager.entity.Score;
import com.studentmanager.entity.Student;
import com.studentmanager.service.ScoreService;
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
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    /**
     * 添加成绩
     *
     * @param request
     * @param response
     */
    @RequestMapping("/addScore")
    @ResponseBody
    public AjaxResult addScore(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String scores = request.getParameter("Scores");
        String credit = request.getParameter("Credit");
        Score score = new Score();
        score.setScores(scores);
        score.setCredit(credit);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = scoreService.scoreInput(score);
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
     * 获取成绩信息
     *
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("/getScore")
    @ResponseBody
    public Object getStudent(@RequestParam("limit") String limit, @RequestParam("page") String page) {
        int lim = Integer.parseInt(limit);
        int start = (Integer.parseInt(page) - 1) * lim;
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("pagesize", lim);
        List<Score> allScore = scoreService.queryList(map);
        List<Score> score = new ArrayList<>();
        for (int i = 0; i < allScore.size(); i++) {
            Integer id = allScore.get(i).getId();
            Integer c_no = allScore.get(i).getC_no();
            String c_name = allScore.get(i).getC_name();
            String t_name = allScore.get(i).getT_name();
            String scores = allScore.get(i).getScores();
            String credit = allScore.get(i).getCredit();
            score.add(new Score(id, c_no, c_name, t_name, scores, credit));
        }
        int total = scoreService.scoreCount();
        System.out.println(total);
        tableModel l = tableModel.data(total, score);
        return JSON.toJSON(l);
    }

}
