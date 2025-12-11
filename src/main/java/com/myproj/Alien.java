package com.myproj;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alien")
public class Alien {

    @Id
    @Column(name = "id")
    private int aid;
    @Column(name = "name")
    private String aname;
    @Column(name = "color")
    private String color;

    public int getAid() {
        return aid;
    }

    public String getAname() {
        return aname;
    }

    public String getColor() {
        return color;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
