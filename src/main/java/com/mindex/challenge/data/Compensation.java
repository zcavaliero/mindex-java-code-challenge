package com.mindex.challenge.data;

public class Compensation {
    private String employeeId;
    private Integer salary;
    private String effectiveDate;


    public Compensation() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getSalary() { return salary; }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

}
