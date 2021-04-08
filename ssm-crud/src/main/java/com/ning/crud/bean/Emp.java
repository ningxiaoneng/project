package com.ning.crud.bean;

public class Emp {
    private Integer empId;

    private String empName;

    private String sex;

    private String email;

    private Integer deptId;

    private  Dept dept;

    public Emp() {

    }

    public Emp(Integer empId, String empName, String sex, String email, Integer deptId) {
        this.empId = empId;
        this.empName = empName;
        this.sex = sex;
        this.email = email;
        this.deptId = deptId;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}