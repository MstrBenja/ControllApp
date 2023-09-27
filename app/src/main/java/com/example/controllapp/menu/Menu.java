package com.example.controllapp.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;

import com.example.controllapp.R;
import com.example.controllapp.menu.eventos.Events;
import com.example.controllapp.menu.info.Info;
import com.example.controllapp.menu.task.Tasks;

public class Menu extends AppCompatActivity {

    private Button info, eventos, tareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        info = (Button) findViewById(R.id.info);
        eventos = (Button) findViewById(R.id.eventos);
        tareas = (Button) findViewById(R.id.tareas);

    }// oncreate

    private void irEventos(View view){
        Intent r = new Intent(Menu.this, Events.class);
        startActivity(r);
    }// method

    private void irInfo(View view){
        Intent r = new Intent(Menu.this, Info.class);
        startActivity(r);
    }// method

    private void irTareas(View view){
        Intent r = new Intent(Menu.this, Tasks.class);
        startActivity(r);
    }// method

}// class