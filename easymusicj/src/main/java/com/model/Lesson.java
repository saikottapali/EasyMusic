package com.model;

import java.util.UUID;

public class Lesson {

    private String title;
    private boolean completed = false;
    private UUID id;

    public Lesson(String title, UUID id) {
        this.title = title;
        this.id = UUID.randomUUID(); //Generate a unique UUID
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
}
