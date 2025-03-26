package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student extends User {
    private List<Lesson> instructions;
    private Instructor instructor;

    public Student(String username, String password, String email, String firstName,
     String lastName, ArrayList<Lesson> instructions, Instructor instructor, int practiceStreak, 
     Instrument selectedInstrument, List<Song> composedSongs, UUID id, boolean isLoggedIn) {
        super(id, username, password, email, firstName, lastName, practiceStreak, selectedInstrument, composedSongs, isLoggedIn);
        this.instructions = instructions;
        this.instructor = instructor;
    }

    // Adds a lesson to the student's list of instructions
    public void addInstruction(Lesson lesson) {
        instructions.add(lesson);
    }

    // Completes a lesson and marks it as completed
    public void completeInstruction(Lesson lesson) {
        if (instructions.contains(lesson)) {
            System.out.println("Completed lesson: " + lesson.getTitle());
            lesson.completeLesson(); // Make sure `Lesson` has the completeLesson method
        } else {
            System.out.println("Lesson not found in the assigned instructions.");
        }
    }

    public List<Lesson> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Lesson> instructions) {
        this.instructions = instructions;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}

