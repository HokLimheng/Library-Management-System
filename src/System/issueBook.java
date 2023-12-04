package System;

import java.sql.Date;

public class issueBook {
    private int id;
    private String title;
    private String studentName;

    private int studentID;
    private java.sql.Date issueDate;
    private java.sql.Date dueDate;
    private String status;


    public issueBook(int id, String title, String studentName, int studentID, Date issueDate, Date dueDate, String status) {
        this.id = id;
        this.title = title;
        this.studentName = studentName;
        this.studentID = studentID;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public issueBook(int id, String title, String studentName, Date issueDate, Date dueDate) {
        this.id = id;
        this.title = title;
        this.studentName = studentName;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
