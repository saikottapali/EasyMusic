package com.model;

import java.util.List;

public class Student extends User {
    private List<Lesson> instructions;
    private Instructor instructor;

    public Student(String username, String password, String email, String firstName, String lastName, List<Lesson> instructions, Instructor instructor) {
        super(username, password, email, firstName, lastName);
        this.instructions = instructions;
        this.instructor = instructor;
    }

    public void completeInstruction(Lesson lesson) {
        if (instructions.contains(lesson)) {
            System.out.println("Completed lesson: " + lesson.getTitle());
            lesson.completeLesson();
        } else {
            System.out.println("Lesson not found in the assigned instructions.");
        }
    }
}
