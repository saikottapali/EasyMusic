package com.model;

import java.util.List;

public class Instructor extends User {
    private List<Student> students;

    public Instructor(String username, String password, String email, String firstName, String lastName, List<Student> students) {
        super(username, password, email, firstName, lastName);
        this.students = students;
    }

    public void assignInstruction(Lesson lesson, Student student) {
        System.out.println("Assigned lesson: " + lesson.getTitle() + " to student: " + student.getUsername());
    }
}
