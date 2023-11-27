package com.example.controllapp.menu.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.controllapp.R;

public class add_Event extends AppCompatActivity {

    private EditText nombreEvento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


    }
}