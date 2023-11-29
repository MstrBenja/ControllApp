package com.example.controllapp.menu.task;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controllapp.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class to_do_task_Fragment extends Fragment {
    private Button agregarTarea;
    private LinearLayout ly, box;

    private TextView prueba;
    private View estructura_tarea;
    private String titulo;
    private String informacion;
    private TextView tituloTarea, infoTarea;

    public static List<Tareas> listaTareas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_to_do_task_, container, false);

        estructura_tarea = getLayoutInflater().inflate(R.layout.task_estructure, null);

        // assignation
        ly = (LinearLayout) rootView.findViewById(R.id.taskBox);
        agregarTarea = (Button) rootView.findViewById(R.id.agregarTarea);
        box = (LinearLayout) rootView.findViewById(R.id.taskBox);
        prueba = (TextView) rootView.findViewById(R.id.prueba);

        listaTareas = new ArrayList<Tareas>();

        // open other tab
        irAgregarInfo(rootView);

        // method to put all the info all ready created
        try {
            infoGuardada(estructura_tarea, ly, rootView);
        }catch (Exception E) {
            String mensaje = E.getMessage().toString();
            Toast.makeText(rootView.getContext(), mensaje, Toast.LENGTH_SHORT).show();
        }

        // method if the task is checked
        //tareaFinalizada(estructura_tarea);

        return rootView;

    }

    // this method is created to insert all the info allready created en the bbdd
    public void infoGuardada(View estructura_tarea, LinearLayout taskBox, View rootView){

        if (listaTareas.isEmpty()) {
            Toast.makeText(rootView.getContext(), "No hay nada", Toast.LENGTH_SHORT).show();
        }else{
            for(Tareas lista : listaTareas){

                tituloTarea = (TextView) estructura_tarea.findViewById(R.id.tituloTarea);
                infoTarea = (TextView) estructura_tarea.findViewById(R.id.informacionTarea);

                informacion = lista.getInfo();
                titulo = lista.getTitulo();

                tituloTarea.setText(titulo);
                infoTarea.setText(informacion);


                boolean activo =  lista.estaActivo();

                if(activo){
                    taskBox.addView(estructura_tarea);
                }
            }
        }
    }

    // this method is for open other activity
    public void irAgregarInfo(View view){

        agregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(to_do_task_Fragment.super.getContext(), add_Task.class);
                startActivity(intent);
            }
        });
    }

    public void tareaFinalizada(View estructura_tarea){

        String titulo = String.valueOf((TextView) estructura_tarea.findViewById(R.id.tituloTarea));
        CheckBox checked = (CheckBox) estructura_tarea.findViewById(R.id.confirmacion);

        if(checked.isChecked()){

            for(Tareas lista : listaTareas){
                if(titulo == lista.getTitulo()){
                    lista.setActivo(false);
                    estructura_tarea.setVisibility(View.GONE);
                }
            }
        }
    }

}