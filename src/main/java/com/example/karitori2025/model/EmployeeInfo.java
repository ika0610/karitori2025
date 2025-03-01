package com.example.karitori2025.model;

import javax.persistence.*;

@Entity
@Table(name = "employee_info")
public class EmployeeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "task_no", nullable = false)
    private String taskNo;

    @Column(name = "employee_department")
    private String employeeDepartment; // 社員部署

    @Column(name = "employee_project")
    private String employeeProject;    // 社員PJ

    @Column(name = "employee_number")
    private Integer employeeNumber;    // 社員番号

    @Column(name = "employee_name")
    private String employeeName;       // 社員名

    @Column(name = "answer_status")
    private String answerStatus;       // 回答状況

    @Column(name = "answer_remark", columnDefinition = "TEXT")
    private String answerRemark;       // 回答備考

    // Getter/Setter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTaskNo() {
        return taskNo;
    }
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
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
    public Integer getEmployeeNumber() {
        return employeeNumber;
    }
    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getAnswerStatus() {
        return answerStatus;
    }
    public void setAnswerStatus(String answerStatus) {
        this.answerStatus = answerStatus;
    }
    public String getAnswerRemark() {
        return answerRemark;
    }
    public void setAnswerRemark(String answerRemark) {
        this.answerRemark = answerRemark;
    }
}
