package com.model;

import java.util.UUID;

public class ComposedSongEntry {
    
    private UUID id;
    private String title;

    public ComposedSongEntry(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
