package com.studentmanager.entity;

public class Student {
    private Integer id;
    private String username;
    private String s_name;
    private String password;
    private String sCollege;
    private String sProfessional;
    private Integer classId;
    private String sex;

    public Student(Integer id, String username, String s_name, String sex, String sCollege, String sProfessional, Integer classId) {
        super();
        this.id = id;
        this.username = username;
        this.s_name = s_name;
        this.sex = sex;
        this.sCollege = sCollege;
        this.sProfessional = sProfessional;
        this.classId = classId;
    }

    public Student() {

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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getsCollege() {
        return sCollege;
    }

    public void setsCollege(String sCollege) {
        this.sCollege = sCollege;
    }

    public String getsProfessional() {
        return sProfessional;
    }

    public void setsProfessional(String sProfessional) {
        this.sProfessional = sProfessional;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
