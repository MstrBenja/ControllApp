package com.example.controllapp.menu.eventos;

import static com.example.controllapp.inicioUsuario.InicioSesion.conn;
import static com.example.controllapp.inicioUsuario.InicioSesion.listaEventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.controllapp.R;

import java.util.UUID;

public class add_Event extends AppCompatActivity {

    private EditText nombreEvento;
    private Button agregar;
    private Spinner colores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        nombreEvento = (EditText) findViewById(R.id.eventName);
        agregar = (Button) findViewById(R.id.agregar);
        colores = (Spinner) findViewById(R.id.colores);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.combo_colores , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        colores.setAdapter(adapter);

        guardarEvento();

    }

    public void guardarEvento(){

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nombreEvento.equals("") || colores.getSelectedItemPosition() == 0){
                    Toast.makeText(add_Event.this, "Nada debe quedar en blanco!!", Toast.LENGTH_SHORT).show();
                }else{
                    String nombre = nombreEvento.getText().toString();
                    String color = colores.getSelectedItem().toString();

                    try {
                        Events evento = new Events(UUID.randomUUID().toString(), nombre, color);
                        //conn.registrarEvento(evento);
                        listaEventos.add(evento);
                    }catch (Exception E){
                        Toast.makeText(add_Event.this, E.toString(), Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(add_Event.this, EventsMain.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}