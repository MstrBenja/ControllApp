package com.example.controllapp.DB;

import android.app.Activity;

import java.util.List;

public class Controller {

    private Singleton db;

    public Controller (Singleton db){
        this.db = db;
    }

    public List<User> verificarUser(User user, Activity activity){
        DAO dao = new DAO(db);
        List<User> lista = dao.verificarUser(user, activity);
        return lista;
    }

    public boolean registrarUser(User user){
        DAO dao = new DAO(db);
        boolean verif = dao.registrarUser(user);
        return true;
    }

    public void registrarTarea(){
        DAO dao = new DAO(db);
        dao.registrarTask();
    }

    public void retornarTareas(){
        DAO dao = new DAO(db);
        dao.retornarTask();
    }

}
