package com.example.controllapp.DB;

import static com.example.controllapp.inicioUsuario.InicioSesion.dbReference;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.controllapp.menu.eventos.Events;
import com.example.controllapp.menu.task.Tareas;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DAO {

    private List<User> listUser;
    private List<Tareas> listTask;
    private List<Events> listEvents;
    private boolean verificacion = false;

    public DAO(){
    }

    public interface DataCallback<T> {
        void onDataLoaded(List<T> data);
        void onError(String errorMessage);
    }

    public List<User> retornarUsers() {

        dbReference.child("Usuarios").addValueEventListener(new ValueEventListener() {

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
        dbReference.child("Usuarios").child(user.getId()).setValue(user);
    }// method

    public void registrarTask(Tareas tarea) {
        dbReference.child("Tareas").child(tarea.getId()).setValue(tarea);
    }// method

    public List<Tareas> retornarTask() {

        dbReference.child("Tareas").addValueEventListener(new ValueEventListener() {

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
        dbReference.child("Eventos").child(evento.getId()).setValue(evento);
    }

    public List<Events> retornarEvents(){

        dbReference.child("Eventos").addValueEventListener(new ValueEventListener() {

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
