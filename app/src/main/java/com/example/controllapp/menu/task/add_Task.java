package com.example.controllapp.menu.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.controllapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class add_Task extends AppCompatActivity {

    private EditText titulo;
    private TextInputEditText info;
    private Button agregar;
    private onTaskAddedListener taskAddedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        titulo = (EditText) findViewById(R.id.tituloName);
        info = (TextInputEditText) findViewById(R.id.informacion);
        agregar = (Button) findViewById(R.id.agregarTareaUI);

        to_do_task_Fragment fragment = (to_do_task_Fragment) getSupportFragmentManager().findFragmentById(R.id.contenedor);

        if(fragment != null && fragment instanceof onTaskAddedListener){
            taskAddedListener = (onTaskAddedListener) fragment;
        }

        guardarTarea();

    }

    public void guardarTarea(){

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle datos = new Bundle();

                //datos.putString("title", titulo.getText().toString());
                //datos.putString("information", info.getText().toString());
                //fragment.setArguments(datos);

                if (taskAddedListener != null){
                    taskAddedListener.onTaskAdded(titulo.getText().toString(), info.getText().toString());
                }

                Intent tareas = new Intent(add_Task.this, Tasks.class);
                tareas.putExtras(datos);
                startActivity(tareas);
            }
        });
    }
}