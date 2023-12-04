package com.example.controllapp.DB;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.controllapp.inicioUsuario.InicioSesion;
import com.example.controllapp.menu.eventos.Events;
import com.example.controllapp.menu.task.Tareas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DAO {

    private List<User> listUser;
    private List<Tareas> listTask;
    private List<Events> listEvents;
    private DatabaseReference dbreference;
    private boolean verificacion = false;

    public DAO(DatabaseReference dbreference){
        this.dbreference = dbreference;
    }

    public List<User> retornarUsers(Context context, User usuario) {

        dbreference.child("Usuarios").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listUser.clear();
                for(DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    User usuario = objSnapshot.getValue(User.class);
                    listUser.add(usuario);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        return listUser;
    }// method

    public void registrarUser(User user) {
        dbreference.child("Usuarios").child(user.getId()).setValue(user);
    }// method

    public void registrarTask(Tareas tarea) {
        dbreference.child("Tareas").child(tarea.getId()).setValue(tarea);
    }// method

    public List<Tareas> retornarTask() {

        dbreference.child("Tareas").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listTask.clear();
                for(DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Tareas tarea = objSnapshot.getValue(Tareas.class);
                    listTask.add(tarea);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        return listTask;
    }// method

    public void registrarEvent(Events evento){
        dbreference.child("Eventos").child(evento.getId()).setValue(evento);
    }

    public List<Events> retornarEvents(){

        dbreference.child("Eventos").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listEvents.clear();
                for(DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Events eventos = objSnapshot.getValue(Events.class);
                    listEvents.add(eventos);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        return listEvents;
    }

}// class
