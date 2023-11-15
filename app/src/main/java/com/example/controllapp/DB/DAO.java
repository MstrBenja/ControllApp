package com.example.controllapp.DB;

import android.app.Activity;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAO {

    private Singleton db;
    private List<User> listUser;
    private DatabaseReference dbreference;

    public DAO(Singleton db){
        this.db = db;
        dbreference = db.getDbReference();
    }

    public List<User> verificarUser(User user, Activity activity) {

        dbreference.child("Usuario").addValueEventListener(new ValueEventListener() {
            ArrayAdapter<User> arrayAdapterUsuario;
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
    }

    public boolean registrarUser(User user) {
        dbreference.child("Usuario").child(user.getId()).setValue(user);
        return true;
    }

    public void registrarTask() {
    }

    public void retornarTask() {
    }
}
