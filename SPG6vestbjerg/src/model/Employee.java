package model;

public class Employee {
    private String name;
    private String jobTitle;
    private int workHours;
    private int employeeId;
    
    public Employee(String name, String jobTitle,int workHours,int employeeId ){
        this.name=name;
        this.jobTitle=jobTitle;
        this.workHours=workHours;
        this.employeeId=employeeId;
    }

    public String getName(){
        return name;
    }

    public String getJobTitle(){
        return jobTitle;
    }

    public int getWorkHours(){
        return workHours;
    }

    public int getEmployeeId(){
        return employeeId;
    }

}
