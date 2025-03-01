package com.example.karitori2025.model;

public class EmployeeMasterEditForm {

    // 社員番号をキー
    private Integer employeeNumber;

    private String employeeDepartment;
    private String employeeProject;
    private String employeeName;

    // ----- Getter / Setter -----
    public Integer getEmployeeNumber() {
        return employeeNumber;
    }
    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }
    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public String getEmployeeProject() {
        return employeeProject;
    }
    public void setEmployeeProject(String employeeProject) {
        this.employeeProject = employeeProject;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
