/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jacobvasxsolution;

import java.util.List;

/**
 *
 * @author Jacob Witbooi
 * 
 */
public class FileHeaders {
    
    private int id;
    private int age;
    private int  gender;
   // private double salary;
    private int salary;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    private double monthlyExpenditure;
    private String occupation;
    private String healthLifestyle;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FileHeaders() {
    }
    public FileHeaders(String[] lineIn)
    {
        try{
        setId(Integer.parseInt(lineIn[0]));
        setAge(Integer.parseInt(lineIn[1]));
        setGender(Integer.parseInt(lineIn[2]));
        //setSalary(Double.parseDouble(lineIn[3]));
        setSalary(Integer.parseInt(lineIn[3]));
        setMonthlyExpenditure(Double.parseDouble(lineIn[4]));
        setOccupation(lineIn[5]);
        setHealthLifestyle(lineIn[6]);
        }
        catch(Exception e)
        {
        System.out.println("Parse Exception:"+e.getMessage());
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
    /*
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    */
    public double getMonthlyExpenditure() {
        return monthlyExpenditure;
    }

    public void setMonthlyExpenditure(double monthlyExpenditure) {
        this.monthlyExpenditure = monthlyExpenditure;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHealthLifestyle() {
        return healthLifestyle;
    }

    public void setHealthLifestyle(String healthLifestyle) {
        this.healthLifestyle = healthLifestyle;
    }

    @Override
    public String toString() {
        return "FileHeaders{" + "id=" + id + ", age=" + age + ", gender=" + gender + ", salary=" + salary + ", monthlyExpenditure=" + monthlyExpenditure + ", occupation=" + occupation + ", healthLifestyle=" + healthLifestyle + '}';
    }

    

    

    

    
    
    
}
