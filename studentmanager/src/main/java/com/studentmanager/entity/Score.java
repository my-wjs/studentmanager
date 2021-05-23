package com.studentmanager.entity;

public class Score {
    private Integer id;
    private Integer c_no;
    private String c_name;
    private String t_name;
    private String scores;
    private String credit;

    public Score() {
    }

    public Score(Integer id, Integer c_no, String c_name, String t_name, String scores, String credit) {
        this.id = id;
        this.c_no = c_no;
        this.c_name = c_name;
        this.t_name = t_name;
        this.scores = scores;
        this.credit = credit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getC_no() {
        return c_no;
    }

    public void setC_no(Integer c_no) {
        this.c_no = c_no;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
