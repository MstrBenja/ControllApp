package com.example.controllapp.menu.task;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.controllapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class to_do_task_Fragment extends Fragment implements onTaskAddedListener {
    private Button agregarTarea;
    private LinearLayout ly, box;

    private TextView prueba;
    private String[] datos;
    private ArrayList<Array> diccionarioTareas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_to_do_task_, container, false);

        // assignation
        ly = (LinearLayout) rootView.findViewById(R.id.padre);
        agregarTarea = (Button) rootView.findViewById(R.id.agregarTarea);
        box = (LinearLayout) rootView.findViewById(R.id.taskBox);
        prueba = (TextView) rootView.findViewById(R.id.prueba);


        datos = new String[2];

        // open other tab
        agregarInfo(rootView);


        // method to put all the info all ready created
        infoGuardada();


        return rootView;

    }

    // this method is created to insert all the info allready created en the bbdd
    public void infoGuardada(){

        //String datosTitulo = tareas.getString("title");
        //String datosInfo = tareas.getString("info");

        if(datos[0] != null && datos[1] != null){
            prueba.setText(datos[0] + " === "+ datos[1]);
        }

        //View vista = getLayoutInflater().inflate(R.layout.task_estructure, null);

    }

    // this method is for open other activity
    public void agregarInfo(View view){

        agregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(to_do_task_Fragment.super.getContext(), add_Task.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onTaskAdded(String title, String info){

        // se guarda los datos en la bbdd
        datos[0] = title;
        datos[1] = info;
        diccionarioTareas.add(datos[]);
    }

}