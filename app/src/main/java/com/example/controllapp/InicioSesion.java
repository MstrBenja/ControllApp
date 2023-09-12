package com.example.controllapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InicioSesion extends AppCompatActivity {

    Button login, register;
    EditText usuario, contrasenha;
    Switch modo;
    TextView titulo, comentario;
    ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);



        // buttons
        login = (Button) findViewById(R.id.loginUI);
        register = (Button) findViewById(R.id.registerUI);

        // text, passwords
        usuario = (EditText) findViewById(R.id.userUI);
        contrasenha = (EditText) findViewById(R.id.passwordUI);

        // other
        modo = (Switch) findViewById(R.id.modeUI);
        titulo = (TextView) findViewById(R.id.titleUI);
        comentario = (TextView) findViewById(R.id.commentUI);
        background = (ImageView) findViewById(R.id.backgroundUI);




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

    public void irRegistro(View v){
        Intent siguiente = new Intent(this,Registro.class);
        startActivity(siguiente);
    }//method

    public void verifyUser(View v){
        String user = usuario.getText().toString();
        String psw = contrasenha.getText().toString();

        /*Data Base Verification
        if(Data base info == tokens writed){
            pass
        }else{
            message of error
        }
        */
        Intent menu = new Intent(this,Menu.class);
        startActivity(menu);
    }//method

    public void cambiarModo(View v, Activity context){

        if(modo.isChecked()){
            int img = getResources().getIdentifier("@drawable/black", null, this.getPackageName());
            background.setImageResource(img);
            titulo.setTextColor(getResources().getColor(R.color.white));
            comentario.setTextColor(getResources().getColor(R.color.white));
            usuario.setTextColor(getResources().getColor(R.color.white));
            usuario.setHintTextColor(context.getResources().getColor(R.color.white));
            contrasenha.setTextColor(getResources().getColor(R.color.white));
            contrasenha.setHintTextColor(context.getResources().getColor(R.color.white));
            modo.setTextColor(getResources().getColor(R.color.white));
        }else{
            int img = getResources().getIdentifier("@drawable/white", null, this.getPackageName());
            background.setImageResource(img);
            titulo.setTextColor(getResources().getColor(R.color.black));
            comentario.setTextColor(getResources().getColor(R.color.black));
            usuario.setTextColor(getResources().getColor(R.color.black));
            usuario.setHintTextColor(context.getResources().getColor(R.color.black));
            contrasenha.setTextColor(getResources().getColor(R.color.black));
            contrasenha.setHintTextColor(context.getResources().getColor(R.color.white));
            modo.setTextColor(getResources().getColor(R.color.black));
        }
    }//method

}//class