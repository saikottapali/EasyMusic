package com.model;

import java.util.List;
import java.util.UUID;

public class Instructor extends User {
    private List<Student> students;

    public Instructor(String username, String password, String email, String firstName, String lastName, 
    List<Student> students, UUID id, int practiceStreak, Instrument selectedInstrument, List<Song> composedSongs) {
        super(id, username, password, email, firstName, lastName, practiceStreak, selectedInstrument, composedSongs);
        this.students = students;
    }
/*
*Issues with getUsername resolved within User class
*/
    public void assignInstruction(Lesson lesson, Student student) {
        System.out.println("Assigned lesson: " + lesson.getTitle() + " to student: " + student.getUsername());
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
