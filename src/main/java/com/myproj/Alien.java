package com.myproj;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Here AlienName is a class but not an Entity class - so mark the class as Embeddable to merge their colum with this table.
 */

@Entity
@Table(name = "alien")
public class Alien {

    @Id
    @Column(name = "id")
    private int aid;
//    @Column(name = "name")
    private AlienName aname;
    @Column(name = "color")
    private String color;

    public int getAid() {
        return aid;
    }

    public AlienName getAname() {
        return aname;
    }

    public String getColor() {
        return color;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setAname(AlienName aname) {
        this.aname = aname;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Fetch and print the well-formated data from the data from the table - Need to give implementation to toString() method of the entity class


    @Override
    public String toString() {
        return "Alien{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
