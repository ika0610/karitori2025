package com.example.karitori2025.model;

public class TaskEditForm {

    // タスクNoをキーにする
    private String taskNo;

    private String taskName;
    private String registrant;
    private String detail;
    private String taskStatus;

    // HTML5 datetime-local用文字列
    private String registrationDateStr;

    // ----- Getter / Setter -----
    public String getTaskNo() {
        return taskNo;
    }
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getRegistrant() {
        return registrant;
    }
    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getRegistrationDateStr() {
        return registrationDateStr;
    }
    public void setRegistrationDateStr(String registrationDateStr) {
        this.registrationDateStr = registrationDateStr;
    }
}
