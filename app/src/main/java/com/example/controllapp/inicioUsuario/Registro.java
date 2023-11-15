package com.example.controllapp.inicioUsuario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.controllapp.DB.Controller;
import com.example.controllapp.DB.Singleton;
import com.example.controllapp.DB.User;
import com.example.controllapp.R;

import java.util.UUID;

public class Registro extends AppCompatActivity {

    // Buttons
    private Button goBack, register;

    // Texts
    private TextView titulo, generoText;
    private EditText name, email, age, userName, password, repeatPassword;

    // other
    private Spinner gender;
    private ScrollView diseno;
    private Singleton db;
    private Controller conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Texts
        name = (EditText) findViewById(R.id.nombreUI);
        email = (EditText) findViewById(R.id.emailUI);
        age = (EditText) findViewById(R.id.edadUI);
        gender = (Spinner) findViewById(R.id.generoUI);
        userName = (EditText) findViewById(R.id.userNameUI);
        password = (EditText) findViewById(R.id.contrasenhaUI);
        repeatPassword = (EditText) findViewById(R.id.repetirPsw);

        // buttons
        register = (Button) findViewById(R.id.registrarseUI);
        goBack = (Button) findViewById(R.id.goBackUI);

        // Spinner
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.combo_genero , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        db = Singleton.getInstance(this);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresar(view);
            }
        });// onclick back to sing up

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });// onclick registrar


    }// oncreate

    private void registrar(){

        User usuario = new User();
        usuario.setId(UUID.randomUUID().toString());
        usuario.setNombre(name.getText().toString());
        usuario.setCorreo(email.getText().toString());
        usuario.setEdad(Integer.parseInt(age.getText().toString()));
        usuario.setGender(gender.getSelectedItem().toString());
        usuario.setUserName(userName.getText().toString());
        usuario.setPassword(password.getText().toString());

        boolean respuesta = conn.registrarUser(usuario);

        if(respuesta){
            AlertDialog.Builder mensaje = new AlertDialog.Builder(Registro.this);
            mensaje.setCancelable(true);
            mensaje.setTitle("Felicidades "+ usuario.getNombre());
            mensaje.setMessage("Has sido registrado satisfactoriamente!");
            mensaje.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent login = new Intent(Registro.this, InicioSesion.class);
                    startActivity(login);
                }
            },5000);

        }else{
            AlertDialog.Builder msg = new AlertDialog.Builder(Registro.this);
            msg.setCancelable(true);
            msg.setTitle("ERROR");
            msg.setMessage("A ocurrido un problema");
            msg.show();
        }
    }// method

    private void regresar(View view){
        Intent regresar = new Intent(this, InicioSesion.class);
        startActivity(regresar);
    }// method

}// class