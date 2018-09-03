package com.yilongzhu.todolite;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TodoList extends Activity {

    List<String> todoItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        setDate();
        EditText editText = (EditText) findViewById(R.id.new_item);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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
        TextView dateText = (TextView) findViewById(R.id.date_text);

        String date = new SimpleDateFormat("EEEE, MMMM d").format(Calendar.getInstance().getTime());

        dateText.setText("Today is " + date);
    }

    public void addListItem() {
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

    public void addListItem(View view) {
        addListItem();
    }
}
