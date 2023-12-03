package com.example.controllapp.menu.task;

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

                // codigo bbbdd

                try {
                    Tareas tarea = new Tareas(titulo.getText().toString(), info.getText().toString(), true);
                    listaTareas.add(tarea);
                }catch (Exception E){
                    String mensaje = E.getMessage().toString();
                    Toast.makeText(add_Task.this, mensaje, Toast.LENGTH_SHORT).show();
                }

                Intent tareas = new Intent(add_Task.this, Tasks.class);
                startActivity(tareas);
                finish();

            }
        });
    }
}