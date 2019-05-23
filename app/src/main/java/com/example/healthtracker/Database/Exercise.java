package com.example.healthtracker.Database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;
    private String  quantity;
    private String description;
    private String timestamp;

    public Exercise() {

    }

    @Ignore
    public Exercise(String title, String quantity, String description, String timestamp) {
        this.title = title;
        this.quantity = quantity;
        this.description = description;
        this.timestamp = timestamp;
    }

    public String toString() {
        return this.title + ", an exercise you do for " + this.quantity + " reps. To do this, you: " + this.description;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String  getQuantity() {
        return quantity;
    }

    public void setQuantity(String  quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
