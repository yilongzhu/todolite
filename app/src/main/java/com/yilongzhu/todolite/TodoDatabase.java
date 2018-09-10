package com.yilongzhu.todolite;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {TodoItem.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoItemDao todoItemDao();
}
