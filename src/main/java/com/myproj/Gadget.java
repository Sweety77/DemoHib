package com.myproj;

import jakarta.persistence.*;

@Entity
public class Gadget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gid;
    private String gname;

    @ManyToOne
    private Kid kid;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }

    @Override
    public String toString() {
        return "Gadget{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                '}';
    }
}
