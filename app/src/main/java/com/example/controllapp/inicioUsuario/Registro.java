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
import android.widget.Toast;

import com.example.controllapp.DB.Controller;
import com.example.controllapp.DB.Singleton;
import com.example.controllapp.DB.User;
import com.example.controllapp.R;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Registro extends AppCompatActivity {

    // Buttons
    private Button goBack, register;

    // Texts
    private TextView titulo, generoText;
    private EditText name, email, age, userName, password;

    // other
    private Spinner gender;
    private ScrollView diseno;
    private Singleton db;
    private Controller conn;
    private boolean respuesta;
    private FirebaseDatabase database;
    private DatabaseReference dbReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference();


        // Texts
        name = (EditText) findViewById(R.id.nombreUI);
        email = (EditText) findViewById(R.id.emailUI);
        age = (EditText) findViewById(R.id.edadUI);
        gender = (Spinner) findViewById(R.id.generoUI);
        userName = (EditText) findViewById(R.id.userNameUI);
        password = (EditText) findViewById(R.id.contrasenhaUI);

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
                registrar(view);
            }
        });// onclick registrar


    }// oncreate

    private void registrar(View view){

        conn = new Controller(db);

        User usuario = new User();
        usuario.setId(UUID.randomUUID().toString());
        usuario.setNombre(name.getText().toString().toLowerCase());
        usuario.setCorreo(email.getText().toString().toLowerCase());
        usuario.setEdad(Integer.parseInt(age.getText().toString()));
        usuario.setGender(gender.getSelectedItem().toString());
        usuario.setUserName(userName.getText().toString().toLowerCase());
        usuario.setPassword(password.getText().toString());


        try {
            dbReference.child("User").child(usuario.getId()).setValue(usuario);
            Toast.makeText(this, "si paso", Toast.LENGTH_SHORT).show();

        }catch (Exception E){
            String problema = E.getMessage();
            Toast.makeText(this, problema, Toast.LENGTH_SHORT).show();
        }

        /*
        if(respuesta){
            AlertDialog.Builder mensaje = new AlertDialog.Builder(Registro.this);
            mensaje.setCancelable(true);
            mensaje.setTitle("Felicidades "+ usuario.getNombre());
            mensaje.setMessage("Has sido registrado satisfactoriamente!");
            mensaje.show();

               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       regresar(new View(view.getContext()));
                   }
               },5000);

        }else{

            AlertDialog.Builder msg = new AlertDialog.Builder(Registro.this);
            msg.setCancelable(true);
            msg.setTitle("ERROR");
            msg.setMessage("A ocurrido un problema");
            msg.show();
        }*/
    }

    private void regresar(View view){
        Intent regresar = new Intent(this, InicioSesion.class);
        startActivity(regresar);
    }// method

}// class