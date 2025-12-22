package com.myproj;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentHQL {

    @Id
    private int rollno;
    private String name;
    private int marks;

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    @Override
    public String toString() {
        return "StudentCache{" +
                "marks=" + marks +
                ", rollno=" + rollno +
                ", name='" + name + '\'' +
                '}';
    }
}
