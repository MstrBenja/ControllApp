package com.example.controllapp.DB;

import android.content.Context;

import com.example.controllapp.menu.eventos.Events;
import com.example.controllapp.menu.task.Tareas;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Controller {

    private DatabaseReference db;
    private DAO dao;

    public Controller (DatabaseReference db){
        this.db = db;
        this.dao = new DAO(db);
    }

    public void registrarUser(User user){

        dao.registrarUser(user);
    }

    public List<User> getUsers(Context context, User usuario){
        List<User> verif = dao.retornarUsers(context, usuario);
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

}
