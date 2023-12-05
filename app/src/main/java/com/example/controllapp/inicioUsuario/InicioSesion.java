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
import com.example.controllapp.DB.DAO;
import com.example.controllapp.DB.Singleton;
import com.example.controllapp.DB.User;
import com.example.controllapp.MQTT.mqttHandler;
import com.example.controllapp.menu.Menu;
import com.example.controllapp.R;
import com.example.controllapp.menu.eventos.Events;
import com.example.controllapp.menu.task.Tareas;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class InicioSesion extends AppCompatActivity {

    // Layout
    private Button login, register;
    private EditText usuarioUI, contrasenha;
    private Switch modo;
    private TextView titulo, comentario;
    private ConstraintLayout disenho;


    // info in the code
    private List<User> listUser;

    public static List<Tareas> listaTareas;
    public static List<Events> listaEventos;

    // DB
    public static DatabaseReference dbReference;
    public static Controller conn;
    public static DAO dao;


    // MQTT ======================================================================
    private static final String BROKER_URL = "tcp://androidteststiqq.cloud.shiftr.io:1883";
    private static final String CLIENT_ID = "ControllApp";
    private mqttHandler mqttHandler;
    // MQTT ======================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);


        listaTareas = new ArrayList<Tareas>();
        listaEventos = new ArrayList<Events>();

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


        // DB
        Singleton.inicializar(getApplicationContext());
        dbReference = Singleton.getDatabase(getApplicationContext());
        conn = new Controller(dbReference);
        dao = new DAO();

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

        String userName = usuarioUI.getText().toString();
        String psw = contrasenha.getText().toString();


        if(userName.isEmpty() || psw.isEmpty()){
            Toast.makeText(this, "No debe estar nada vacio", Toast.LENGTH_SHORT).show();
        }else{
            User usuario = new User();
            usuario.setUserName(userName);
            usuario.setPassword(psw);

            boolean verif = estaRegistrado(usuario);

            if(verif){
                Intent menu = new Intent(this, Menu.class);
                startActivity(menu);
                finish();
            }else{

                Toast.makeText(this, "No existe este usuario", Toast.LENGTH_SHORT).show();
                usuarioUI.setText("");
                contrasenha.setText("");
            }
        }
    }//method

    public boolean estaRegistrado(User usuario){

        List<User> lista = conn.getUsers();

        if(lista == null){
            return false;
        }else{
            for(User list : lista){
                if(usuario.getUserName() == list.getUserName() &&
                    usuario.getPassword() == list.getPassword()){
                    return true;
                }
            }
        }
        return false;

    }

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