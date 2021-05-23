package com.studentmanager.mapper;

import com.studentmanager.entity.Score;
import com.studentmanager.entity.Student;

import java.util.List;
import java.util.Map;

public interface ScoreMapper {

    // 学生成绩录入
    int scoreInput(Score score);

    List<Score> queryList(Map<String, Object> map);

    int scoreCount();

    List<Score> queryScore(Map<String, Object> map);

}
