package com.myproj;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


/*
AlienName class is not an Entity class still all the attributes of this class is column of Alien table.
This is because of the Embeddable Annotation. AlienNmae is use as type for aname variable in Alien class,
which is why all the variables of this class got merged to become column of Alien Table through Alien class.
So, we use @Embeddable Annotation upon the class.
 */
@Embeddable
public class AlienName
{

    @Column(name = "firstname")
    private String fname;
    @Column(name = "middname")
    private String mname;
    @Column(name = "lasrname")
    private String lname;


    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMname() {
        return mname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    @Override
    public String toString() {
        return "AlienName{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", mname='" + mname + '\'' +
                '}';
    }
}
