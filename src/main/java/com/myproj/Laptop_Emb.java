package com.myproj;

import jakarta.persistence.Embeddable;

@Embeddable
public class Laptop_Emb {
    private int lid;
    private String lname;

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
