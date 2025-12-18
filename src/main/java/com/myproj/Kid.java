package com.myproj;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kid")
public class Kid {

    @Id
    @Column(name = "id")
    private int kid;
    private String kname;

    @OneToMany(mappedBy = "kid", fetch = FetchType.EAGER)
    private List<Gadget> gadgets = new ArrayList<>();

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    public List<Gadget> getGadgets() {
        return gadgets;
    }

    public void setGadgets(List<Gadget> gadgets) {
        this.gadgets = gadgets;
    }

    @Override
    public String toString() {
        return "Kid{" +
                "kid=" + kid +
                ", kname='" + kname + '\'' +
                '}';
    }
}
