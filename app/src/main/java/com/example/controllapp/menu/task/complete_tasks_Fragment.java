package com.example.controllapp.menu.task;

import static com.example.controllapp.inicioUsuario.InicioSesion.listaTareas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controllapp.R;

public class complete_tasks_Fragment extends Fragment {

    private LinearLayout taskBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_complete_tasks_, container, false);

        // assignation
        taskBox = (LinearLayout) rootView.findViewById(R.id.taskBox);

        // method to put all the info all ready created
        try {
            infoGuardada(rootView);
        }catch (Exception E) {
            String mensaje = E.getMessage().toString();
            Toast.makeText(rootView.getContext(), mensaje, Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }

    // this method is created to insert all the info allready created en the bbdd
    public void infoGuardada(View rootView){
/*
            try{
                listaTareas = conn.getTasks();
            }catch(Exception E){
                Toast.makeText(rootView.getContext(), E.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }*/

            if(!listaTareas.isEmpty()){

                for(Tareas lista : listaTareas){

                    if(!lista.estaActivo()){

                        View estructura_complete_task = getLayoutInflater().inflate(R.layout.estructure_task_complete, null);
                        TextView title = estructura_complete_task.findViewById(R.id.tituloUI);
                        TextView information = estructura_complete_task.findViewById(R.id.infoUI);

                        String titulo = lista.getTitulo();
                        String informacion = lista.getInfo();

                        title.setText(titulo);
                        information.setText(informacion);


                        if(estructura_complete_task.getParent() != null) {
                            ((ViewGroup) estructura_complete_task.getParent()).removeView(estructura_complete_task);
                        }
                        taskBox.addView(estructura_complete_task);

                    }// if
                }// for
            }// if
    }// method
}