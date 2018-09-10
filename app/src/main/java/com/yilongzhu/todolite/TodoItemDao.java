package com.yilongzhu.todolite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface TodoItemDao {
    @Insert
    void addEntry(TodoItem todoItem);

    @Query("SELECT * FROM todoitem ORDER BY id ASC")
    TodoItem[] getAllEntries();

    @Query("DELETE FROM todoitem")
    void deleteAll();
}
