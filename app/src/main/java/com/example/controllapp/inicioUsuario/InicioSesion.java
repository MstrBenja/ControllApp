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

import com.example.controllapp.menu.Menu;
import com.example.controllapp.R;

public class InicioSesion extends AppCompatActivity {

    private Button login, register;
    private EditText usuario, contrasenha;
    private Switch modo;
    private TextView titulo, comentario;
    private ConstraintLayout disenho;

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
        disenho = (ConstraintLayout) findViewById(R.id.disenho);




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
        String user = usuario.getText().toString();
        String psw = contrasenha.getText().toString();

        /*Data Base Verification
        if(Data base info == tokens writed){
            pass
        }else{
            message of error

        }
        */
        Intent menu = new Intent(this, Menu.class);
        startActivity(menu);
    }//method

    private void cambiarModo(View v, Activity context){

        if(modo.isChecked()){
            disenho.setBackgroundColor(getResources().getColor(R.color.black));
            titulo.setTextColor(getResources().getColor(R.color.white));
            comentario.setTextColor(getResources().getColor(R.color.white));
            usuario.setTextColor(getResources().getColor(R.color.white));
            usuario.setHintTextColor(context.getResources().getColor(R.color.white));
            contrasenha.setTextColor(getResources().getColor(R.color.white));
            contrasenha.setHintTextColor(context.getResources().getColor(R.color.white));
            modo.setTextColor(getResources().getColor(R.color.white));
        }else{
            disenho.setBackgroundColor(getResources().getColor(R.color.white));
            titulo.setTextColor(getResources().getColor(R.color.black));
            comentario.setTextColor(getResources().getColor(R.color.black));
            usuario.setTextColor(getResources().getColor(R.color.black));
            usuario.setHintTextColor(context.getResources().getColor(R.color.black));
            contrasenha.setTextColor(getResources().getColor(R.color.black));
            contrasenha.setHintTextColor(context.getResources().getColor(R.color.black));
            modo.setTextColor(getResources().getColor(R.color.black));
        }
    }//method

}//class