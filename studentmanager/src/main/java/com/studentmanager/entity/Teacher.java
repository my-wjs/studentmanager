package com.studentmanager.entity;

public class Teacher {
    private Integer id;
    private String username;
    private String t_name;
    private String password;
    private String sex;
    private String tCollege;
    private String tProfessional;
    private Integer classId;

    public Teacher() {

    }

    public Teacher(Integer id, String username, String t_name, String sex, String tCollege, String tProfessional, Integer classId) {
        this.id = id;
        this.username = username;
        this.t_name = t_name;
        this.sex = sex;
        this.tCollege = tCollege;
        this.tProfessional = tProfessional;
        this.classId = classId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String gettCollege() {
        return tCollege;
    }

    public void settCollege(String tCollege) {
        this.tCollege = tCollege;
    }

    public String gettProfessional() {
        return tProfessional;
    }

    public void settProfessional(String tProfessional) {
        this.tProfessional = tProfessional;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
