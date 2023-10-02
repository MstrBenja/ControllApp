package com.example.controllapp.menu.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.controllapp.R;
import com.example.controllapp.menu.Menu;

public class Events extends AppCompatActivity {

    private Button eat, sleep, bath, study;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eat = (Button) findViewById(R.id.eat);
        sleep = (Button) findViewById(R.id.sleep);
        bath = (Button) findViewById(R.id.bath);
        study = (Button) findViewById(R.id.study);

    }// onCreate

    public void aMenuFromEvents(View v){
        Intent menu = new Intent(this, Menu.class);
        startActivity(menu);
    }// method

    public void evento(){
        Toast.makeText(Events.this, "Se está contabilizando el tiempo", Toast.LENGTH_SHORT).show();

    }

    public void agregar(){
        Toast.makeText(Events.this, "Pronto habrá una funcionalidad de agregar eventos", Toast.LENGTH_SHORT).show();
    }

}// class