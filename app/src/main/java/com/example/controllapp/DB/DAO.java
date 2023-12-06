package com.example.controllapp.DB;


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAO {

    private List<User> listUser;
    private List<Tareas> listTask;
    private List<Events> listEvents;
    private DatabaseReference dbReference;
    private boolean verificacion = false;
    private User usuarioConectado;

    public DAO(DatabaseReference dbReference){
        this.dbReference = dbReference;
    }



    public void usuarioConectado(User usuario){

        List<User> lista = retornarUsers(dbReference);

        for(User listaUsuarios : lista){
            if(usuario.getUserName() == listaUsuarios.getUserName() &&
                    usuario.getPassword() == listaUsuarios.getPassword() &&
                    usuario.getNombre() == listaUsuarios.getNombre()){

                this.usuarioConectado = listaUsuarios;
                break;
            }
        }
    }

    public User getUsuarioConectado(){
        return usuarioConectado;
    }


    public List<User> retornarUsers(DatabaseReference dbReference) {

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



    public void registrarUser(DatabaseReference dbReference,User user) {
        dbReference.child("Usuarios").child(user.getId()).setValue(user);
    }// method



    public void registrarTask(DatabaseReference dbReference, Tareas tarea) {

        DatabaseReference usuarioRef = dbReference.child("Usuarios").child(usuarioConectado.getId());

        usuarioRef.child("tareas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Tareas> listaTareas = new ArrayList<>();

                // if theres existing task, add them to the list
                if (dataSnapshot.exists()) {
                    for (DataSnapshot tareaSnapshot : dataSnapshot.getChildren()) {
                        Tareas tareaExistente = tareaSnapshot.getValue(Tareas.class);
                        listaTareas.add(tareaExistente);
                    }
                }

                // add the new task to the list
                listaTareas.add(tarea);

                // update the list of tasks of the user
                usuarioRef.child("tareas").setValue(listaTareas);

                // aving the task in a individual "table"
                dbReference.child("Tareas").child(tarea.getId()).setValue(tarea);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbReference.child("tareas").child(tarea.getId()).setValue(tarea);

    }// method




    public List<Tareas> retornarTask(DatabaseReference dbReference) {

        dbReference.child("tareas").addValueEventListener(new ValueEventListener() {

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




    public void registrarEvent(DatabaseReference dbReference, Events evento){

        DatabaseReference usuarioRef = dbReference.child("Usuarios").child(usuarioConectado.getId());

        usuarioRef.child("eventos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Events> listaEventos = new ArrayList<>();

                // if theres existing task, add them to the list
                if (dataSnapshot.exists()) {
                    for (DataSnapshot tareaSnapshot : dataSnapshot.getChildren()) {
                        Events eventoExistente = tareaSnapshot.getValue(Events.class);
                        listaEventos.add(eventoExistente);
                    }
                }

                // add the new event to the list
                listaEventos.add(evento);

                // update the list of events of the user
                usuarioRef.child("tareas").setValue(listaEventos);

                // aving the task in a individual "table"
                dbReference.child("Tareas").child(evento.getId()).setValue(evento);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbReference.child("tareas").child(evento.getId()).setValue(evento);
    }




    public List<Events> retornarEvents(DatabaseReference dbReference){

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
