package com.example.controllapp.DB;

import android.app.Activity;
import android.content.Intent;

import com.example.controllapp.inicioUsuario.InicioSesion;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Singleton {
    private static Singleton instance;
    private FirebaseDatabase db;
    private DatabaseReference dbReference;
    public String value;


    private Singleton(Activity activity) {
       this.value = value;
        FirebaseApp.initializeApp(activity.getApplicationContext());
        db = FirebaseDatabase.getInstance();
        dbReference = db.getReference();
    }

    public static Singleton getInstance(Activity activity) {
        if (instance == null) {
            instance = new Singleton(activity);
        }
        return instance;
    }

    public FirebaseDatabase getDb() {
        return db;
    }

    public void setDb(FirebaseDatabase db) {
        this.db = db;
    }

    public DatabaseReference getDbReference() {
        return dbReference;
    }

    public void setDbReference(DatabaseReference dbReference) {
        this.dbReference = dbReference;
    }
}
