package com.studentmanager.service;

import com.studentmanager.entity.Score;
import com.studentmanager.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreService implements ScoreMapper {
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public int scoreInput(Score score) {
        return scoreMapper.scoreInput(score);
    }

    @Override
    public List<Score> queryList(Map<String, Object> map) {
        return scoreMapper.queryList(map);
    }

    @Override
    public int scoreCount() {
        return scoreMapper.scoreCount();
    }

    @Override
    public List<Score> queryScore(Map<String, Object> map) {
        return scoreMapper.queryScore(map);
    }

}
