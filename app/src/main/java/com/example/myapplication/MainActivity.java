package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etCategory;
    private Button btnAdd;
    private RecyclerView rvTasks;
    private TaskAdapter adapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etCategory = findViewById(R.id.etCategory);
        btnAdd = findViewById(R.id.btnAdd);
        rvTasks = findViewById(R.id.rvTasks);

        taskList = new ArrayList<>();
        taskList.add(new Task("Przygotować raport", "Praca"));
        taskList.add(new Task("Zrobić zakupy", "Dom"));

        adapter = new TaskAdapter(taskList);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String category = etCategory.getText().toString().trim();

                if (title.isEmpty()) {
                    etTitle.setError("Podaj nazwę zadania");
                    Toast.makeText(MainActivity.this, "Nazwa zadania nie może być pusta", Toast.LENGTH_SHORT).show();
                    return;
                }

                taskList.add(new Task(title, category));
                adapter.notifyItemInserted(taskList.size() - 1);
                rvTasks.scrollToPosition(taskList.size() - 1);

                etTitle.setText("");
                etCategory.setText("");
            }
        });
    }
}
