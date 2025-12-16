package com.myproj;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    private int rollno;
    private String name;
    private int marks;
    private Laptop_Emb  laptopEmb;

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Laptop_Emb getLaptopEmb() {
        return laptopEmb;
    }

    public void setLaptopEmb(Laptop_Emb laptopEmb) {
        this.laptopEmb = laptopEmb;
    }
}
