package com.example.controllapp.inicioUsuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controllapp.DB.Controller;
import com.example.controllapp.DB.Singleton;
import com.example.controllapp.DB.User;
import com.example.controllapp.menu.Menu;
import com.example.controllapp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class InicioSesion extends AppCompatActivity {

    private Button login, register;
    private EditText usuarioUI, contrasenha;
    private Switch modo;
    private TextView titulo, comentario;
    private ConstraintLayout disenho;
    private Singleton db;
    private Controller conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);


        // buttons
        login = (Button) findViewById(R.id.loginUI);
        register = (Button) findViewById(R.id.registerUI);

        // text, passwords
        usuarioUI = (EditText) findViewById(R.id.userUI);
        contrasenha = (EditText) findViewById(R.id.passwordUI);

        // other
        modo = (Switch) findViewById(R.id.modeUI);
        titulo = (TextView) findViewById(R.id.titleUI);
        comentario = (TextView) findViewById(R.id.commentUI);
        disenho = (ConstraintLayout) findViewById(R.id.disenho);

        db = Singleton.getInstance(this);

        // register onclick function
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irRegistro(view);
            }
        });

        // login onclick function
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyUser(view);
            }
        });

        // night/day mode onlick function
        modo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarModo(view, InicioSesion.this);
            }
        });

    }//onclick

    private void irRegistro(View v){
        Intent siguiente = new Intent(this, Registro.class);
        startActivity(siguiente);
    }//method

    private void verifyUser(View v){

        String userName = usuarioUI.getText().toString();
        String psw = contrasenha.getText().toString();

        User usuario = new User(userName, psw);

        List<User> verif = conn.verificarUser(usuario,this);

        //Data Base Verification
        if(verif.isEmpty()){
            Toast.makeText(this, "no hay na'", Toast.LENGTH_SHORT).show();
        }else{
            for (User lista : verif){
                if(lista.getNombre() == userName && lista.getPassword() == psw){
                    Intent menu = new Intent(this, Menu.class);
                    startActivity(menu);
                }else{
                    Toast.makeText(this, "Hubo un error, no existe ese usuario", Toast.LENGTH_SHORT).show();
                    usuarioUI.setText("");
                    contrasenha.setText("");
                }
            }
        }


    }//method

    private void cambiarModo(View v, Activity context){

        if(modo.isChecked()){
            disenho.setBackgroundColor(getResources().getColor(R.color.black));
            titulo.setTextColor(getResources().getColor(R.color.white));
            comentario.setTextColor(getResources().getColor(R.color.white));
            usuarioUI.setTextColor(getResources().getColor(R.color.white));
            usuarioUI.setHintTextColor(context.getResources().getColor(R.color.white));
            contrasenha.setTextColor(getResources().getColor(R.color.white));
            contrasenha.setHintTextColor(context.getResources().getColor(R.color.white));
            modo.setTextColor(getResources().getColor(R.color.white));
        }else{
            disenho.setBackgroundColor(getResources().getColor(R.color.white));
            titulo.setTextColor(getResources().getColor(R.color.black));
            comentario.setTextColor(getResources().getColor(R.color.black));
            usuarioUI.setTextColor(getResources().getColor(R.color.black));
            usuarioUI.setHintTextColor(context.getResources().getColor(R.color.black));
            contrasenha.setTextColor(getResources().getColor(R.color.black));
            contrasenha.setHintTextColor(context.getResources().getColor(R.color.black));
            modo.setTextColor(getResources().getColor(R.color.black));
        }
    }//method

}//class