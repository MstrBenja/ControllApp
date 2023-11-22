package com.example.controllapp.DB;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Controller {

    private DatabaseReference db;
    private DAO dao;

    public Controller (DatabaseReference db){
        this.db = db;
        this.dao = new DAO(db);
    }

    public List<User> verificarUser(Context context, User usuario){
        List<User> verif = dao.verificarUser(context, usuario);
        return verif;
    }

    public void registrarUser(User user){
        dao.registrarUser(user);
    }

    public void registrarTarea(){
        dao.registrarTask();
    }

    public void retornarTareas(){
        dao.retornarTask();
    }

}
