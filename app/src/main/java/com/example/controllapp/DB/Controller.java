package com.example.controllapp.DB;

import static com.example.controllapp.inicioUsuario.InicioSesion.dao;

import android.content.Context;

import com.example.controllapp.menu.eventos.Events;
import com.example.controllapp.menu.task.Tareas;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Controller {

    private DatabaseReference db;

    public Controller (DatabaseReference db){
        this.db = db;
    }

    public void registrarUser(User user){
        dao.registrarUser(user);
    }

    public List<User> getUsers(){
        List<User> verif = dao.retornarUsers();
        return verif;
    }

    public void registrarTarea(Tareas task){
        dao.registrarTask(task);
    }

    public List<Tareas> getTasks(){
        List<Tareas> lista = dao.retornarTask();
        return lista;
    }

    public void registrarEvento(Events evento){
        dao.registrarEvent(evento);
    }

    public List<Events> getEvents(){
        List<Events> lista = dao.retornarEvents();
        return lista;
    }

}
