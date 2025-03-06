package com.model;

public class Lesson {

    private String title;
    private boolean completed = false;

    public Lesson(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void startLesson() {
        System.out.println("Lesson started: " + title);
    }

    public void completeLesson() {
        completed = true;
        System.out.println("Lesson completed!");
    }

    public boolean isCompleted() {
        return completed;
    }
    
}
