package com.yilongzhu.todolite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TodoList extends Activity {

    List<String> todoItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
    }

    public void addListItem(View view) {
        ListView todoList = (ListView) findViewById(R.id.todo_list);
        EditText newItem = (EditText) findViewById(R.id.new_item);
        String newItemText = newItem.getText().toString();

        if (!newItemText.equals("")) {
            todoItems.add(newItemText);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoItems);
            todoList.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
            newItem.setText("");
        }
    }
}
