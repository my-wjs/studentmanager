package com.studentmanager.entity;

public class Course {
    private Integer c_id;
    private Integer c_no;
    private String c_name;
    private String c_information;
    private Integer c_max;
    private Integer c_num;

    public Course() {
    }

    public Course(Integer c_id, Integer c_no, String c_name, String c_information, Integer c_max, Integer c_num) {
        this.c_id = c_id;
        this.c_no = c_no;
        this.c_name = c_name;
        this.c_information = c_information;
        this.c_max = c_max;
        this.c_num = c_num;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
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

    public String getC_information() {
        return c_information;
    }

    public void setC_information(String c_information) {
        this.c_information = c_information;
    }

    public Integer getC_max() {
        return c_max;
    }

    public void setC_max(Integer c_max) {
        this.c_max = c_max;
    }

    public Integer getC_num() {
        return c_num;
    }

    public void setC_num(Integer c_num) {
        this.c_num = c_num;
    }
}
