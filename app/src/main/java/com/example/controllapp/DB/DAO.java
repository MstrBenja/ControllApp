package com.example.controllapp.DB;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.controllapp.inicioUsuario.InicioSesion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DAO {

    private List<User> listUser;
    private DatabaseReference dbreference;
    ArrayAdapter<User> arrayAdapterUsuario;
    private boolean verificacion = false;

    public DAO(DatabaseReference dbreference){
        this.dbreference = dbreference;
    }

    public List<User> verificarUser(Context context, User usuario) {

        dbreference.child("Usuario").addValueEventListener(new ValueEventListener() {

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

    public void registrarUser(User user) {
        dbreference.child("Usuario").child(user.getId()).setValue(user);
    }

    public void registrarTask() {
    }

    public void retornarTask() {
    }
}
