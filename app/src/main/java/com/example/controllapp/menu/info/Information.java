package com.example.controllapp.menu.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.controllapp.R;
import com.example.controllapp.menu.Menu;
import com.example.controllapp.menu.eventos.infoEventos;

public class Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }// onCreate

    public void aMenuFromInfo(View v){
        Intent menu = new Intent(this, Menu.class);
        startActivity(menu);
    }// method

    public void infoEvents(){
        Intent info = new Intent(this, infoEventos.class);
        startActivity(info);
    }
}// class