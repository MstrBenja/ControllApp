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
import com.example.controllapp.MQTT.mqttHandler;
import com.example.controllapp.menu.Menu;
import com.example.controllapp.R;
import com.example.controllapp.menu.task.Tareas;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InicioSesion extends AppCompatActivity {

    private Button login, register;
    private EditText usuarioUI, contrasenha;
    private Switch modo;
    private TextView titulo, comentario;
    private ConstraintLayout disenho;
    private DatabaseReference dbReference;
    private Controller conn;
    private List<User> listUser;
    private boolean verif;

    // MQTT ======================================================================
    private static final String BROKER_URL = "tcp://androidteststiqq.cloud.shiftr.io:1883";
    private static final String CLIENT_ID = "ControllApp";

    private mqttHandler mqttHandler;
    // MQTT ======================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);


        // MQTT ====================================================================
        try{
            mqttHandler = new mqttHandler();
            mqttHandler.connect(BROKER_URL,CLIENT_ID, this);

            subscribeToTopic("Tema1");
            publishMessage("Tema1", "a");
        }catch (Exception E){
            String mensaje = E.getMessage().toString();
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }
        // MQTT ====================================================================

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

        Singleton.inicializar(getApplicationContext());

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
                irMenu(view);
            }
        });

        // night/day mode onlick function
        modo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarModo(view, InicioSesion.this);
            }
        });

    }//onCreate


    @Override
    protected void onDestroy() {
        mqttHandler.disconnect();
        super.onDestroy();

    }

    private void irRegistro(View v){
        Intent siguiente = new Intent(this, Registro.class);
        startActivity(siguiente);
        finish();
    }//method

    private void verifyUser(View v){

        conn = new Controller(dbReference);
        String userName = usuarioUI.getText().toString();
        String psw = contrasenha.getText().toString();

        User usuario = new User();
        usuario.setUserName(userName);
        usuario.setPassword(psw);

        try {
            listUser = conn.verificarUser(getApplicationContext(), usuario);
        }catch (Exception E){
            String mensaje  = E.getMessage().toString();
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this, "Si pasó el try", Toast.LENGTH_SHORT).show();

        /*
        for(User lista : listUser){
            if(lista.getUserName() == usuario.getUserName() && lista.getPassword() == usuario.getPassword()){
                verif = true;
                break;
            }else{
                verif = false;
            }
        }*/

        //Data Base Verification
        /*
        if(verif){
            Intent menu = new Intent(this, Menu.class);
            startActivity(menu);
            finish();

        }else{
            usuarioUI.setText("");
            contrasenha.setText("");
            Toast.makeText(this, "No se ha encontrado a nadie con esas credenciales...", Toast.LENGTH_LONG).show();
        }*/

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

    public void irMenu(View view){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        finish();
    }

    // MQTT ======================================================================
    private void publishMessage(String topic, String message){
        Toast.makeText(this, "Publishing message: " + message, Toast.LENGTH_SHORT).show();
        mqttHandler.publish(topic,message, this);
    }
    private void subscribeToTopic(String topic){
        Toast.makeText(this, "Subscribing to topic "+ topic, Toast.LENGTH_SHORT).show();
        mqttHandler.subscribe(topic);
    }
    // MQTT ====================================================================

}//class