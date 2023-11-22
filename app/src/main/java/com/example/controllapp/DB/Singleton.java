package com.example.controllapp.DB;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Singleton {
    private static FirebaseDatabase db;
    private static DatabaseReference dbReference;
    public String value;


    public static void inicializar(Context context) {
        FirebaseApp.initializeApp(context.getApplicationContext());
        db = FirebaseDatabase.getInstance();
        dbReference = db.getReference();
    }

    public static DatabaseReference getDatabase(Context context) {
        if (dbReference == null) {
            FirebaseApp.initializeApp(context.getApplicationContext());
            db = FirebaseDatabase.getInstance();
            dbReference = db.getReference();
        }
        return dbReference;
    }
}
