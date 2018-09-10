package com.yilongzhu.todolite;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TodoList extends Activity {

    List<String> todoItemsList = new ArrayList<>();
    EditText newItem;
    private static final String DATABASE_NAME = "todo_db";
    private TodoDatabase todoDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        setDate();

        todoDatabase = Room.databaseBuilder(getApplicationContext(),
                TodoDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        populateList();
        newItem = findViewById(R.id.new_item);
        newItem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addListItem();
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void setDate() {
        TextView dateText = findViewById(R.id.date_text);

        String date = new SimpleDateFormat("EEEE, MMMM d").format(Calendar.getInstance().getTime());

        dateText.setText("Today is " + date);
    }

    public void populateList() {
        TodoItem[] allItems = todoDatabase.todoItemDao().getAllEntries();

        for (int i = 0; i < allItems.length; i++) {
            todoItemsList.add(allItems[i].getEntry());
        }
    }

    public void addListItem() {
        ListView todoList = findViewById(R.id.todo_list);
        String newItemText = newItem.getText().toString();

        if (!newItemText.equals("")) {
            TodoItem newTodoItem = new TodoItem();
            newTodoItem.setEntry(newItemText);

            todoDatabase.todoItemDao().addEntry(newTodoItem);
            todoItemsList.add(newItemText);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoItemsList);
            todoList.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
            newItem.setText("");
        }
    }

    /*public void addListItem() {
        ListView todoList = findViewById(R.id.todo_list);
        String newItemText = newItem.getText().toString();

        if (!newItemText.equals("")) {
            todoItems.add(newItemText);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoItems);
            todoList.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
            newItem.setText("");
        }
    }*/

    public void addListItem(View view) {
        addListItem();
    }
}
