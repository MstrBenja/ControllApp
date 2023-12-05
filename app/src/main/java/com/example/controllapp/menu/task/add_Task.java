package com.example.controllapp.menu.task;

import static com.example.controllapp.inicioUsuario.InicioSesion.conn;
import static com.example.controllapp.inicioUsuario.InicioSesion.listaTareas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controllapp.DB.Tasks;
import com.example.controllapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.UUID;

public class add_Task extends AppCompatActivity {

    private EditText titulo;
    private TextInputEditText info;
    private Button agregar;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        titulo = (EditText) findViewById(R.id.tituloName);
        info = (TextInputEditText) findViewById(R.id.informacion);
        agregar = (Button) findViewById(R.id.agregarTareaUI);

        guardarTarea();

    }

    public void guardarTarea(){

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(titulo.equals("") || info.equals("")){
                    Toast.makeText(add_Task.this, "Nada debe quedar en blanco!!", Toast.LENGTH_SHORT).show();
                }else{
                    String title = titulo.getText().toString();
                    String information = info.getText().toString();

                    try {
                        Tareas tarea = new Tareas(UUID.randomUUID().toString(),title, information, true);
                        //conn.registrarTarea(tarea);
                        listaTareas.add(tarea);
                    }catch (Exception E){
                        Toast.makeText(add_Task.this, E.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(add_Task.this, Tasks.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}