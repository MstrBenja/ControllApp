package com.example.controllapp.DB;

import android.app.Activity;

import java.util.List;

public class Controller {

    private Singleton db;
    private final DAO dao;

    public Controller (Singleton db){
        this.db = db;
        this.dao = new DAO(db);
    }

    public List<User> verificarUser(){
        List<User> lista = dao.verificarUser();
        return lista;
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
