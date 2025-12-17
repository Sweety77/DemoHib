package com.myproj;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * In case of one Student Many laptops (OneToMany rel), where student class laptop is annotated with
 *      @OneToMany
 *     private List<Laptop>  laptop = new ArrayList<>();
 *     Then hibernate creates one extra table called student_laptop to maintain the relationship
 *     but in that case by seeing the tables one cannot identify the relation.
 *     so, one more thing can be done which will avoid an extra table
 *     we can identify the relation by observing the table that.
 *     It can be done by mentioning @ManyTOOne relation in Laptop table with student object in it.
 *     This will create an extra column in the laptop table and link in the student Id to each related
 *     laptop.
 *
 *     Still, each annotation will be responsible to create the relation table or maintain in the main table (Here, 1 Laptop/Student)
 *     or a separate rel table in this case - student_laptop table.
 *
 *     The @ManyToOne
 *     private Student student; - will create a new column 'student_rollno' in Laptop table.
 *     And @OneToMany
 *         private List<Laptop>  laptop = new ArrayList<>(); Student Class - will crate a separate student_laptop table.
 *     As we already have relation shown in the laptop table with student id so we can avoid creation of student_laptop table.
 *     To do that mention @OneToMany(mappedBy = "student") in student table - This won't create a separate table.
 *
 *
 */

@Entity
public class Laptop {

    @Id
    private int lid;
    private String lname;
    @ManyToOne
    private Student student;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
