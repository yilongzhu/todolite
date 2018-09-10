package com.yilongzhu.todolite;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class TodoItem {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String entry;

    public TodoItem() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }
    public String getEntry() {
        return entry;
    }
    public void setEntry(String entry) {
        this.entry = entry;
    }

}
