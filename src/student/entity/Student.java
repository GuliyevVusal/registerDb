package student.entity;

import common.Person;

import java.io.Serializable;

public class Student extends Person {

    private String university;

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
