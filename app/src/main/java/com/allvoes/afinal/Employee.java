package com.allvoes.afinal;


public class Employee {
    private int EmpID;
    private String EmpName;
    private String EmpBirth;
    private String Email;

    public Employee(){

    }
    public Employee(int empID, String empName, String empBirth, String email) {
        this.EmpID = empID;
        this.EmpName = empName;
        this.EmpBirth = empBirth;
        this.Email = email;
    }

    public Employee(String empName, String empBirth, String email) {
        this.EmpName = empName;
        this.EmpBirth = empBirth;
        this.Email = email;
    }

    public int getEmpID() {
        return EmpID;
    }

    public void setEmpID(int empID) {
        EmpID = empID;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getEmpBirth() {
        return EmpBirth;
    }

    public void setEmpBirth(String empBirth) {
        EmpBirth = empBirth;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "Employee ID: " + this.EmpID + " - " + "Name: " + this.EmpName + " - " + "DoB: " + this.EmpBirth + " - "
                + "Email: " + this.Email + "\n";
    }
}
