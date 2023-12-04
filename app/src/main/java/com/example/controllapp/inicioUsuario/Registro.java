package com.example.controllapp.inicioUsuario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
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
    public DatabaseReference dbReference;


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

        // buttons
        register = (Button) findViewById(R.id.registrarseUI);
        goBack = (Button) findViewById(R.id.goBackUI);

        // Spinner
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.combo_genero , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        dbReference = Singleton.getDatabase(getApplicationContext());


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

        int posicion = gender.getSelectedItemPosition();

        if(email.equals("") ||
                name.equals("") ||
                Integer.parseInt(age.getText().toString()) < 0 ||
                posicion == 0 ||
                userName.equals("") || password.equals("")){

            Toast.makeText(this, "Todos los campos deben estar rellenados", Toast.LENGTH_SHORT).show();

        }else{

            User usuario = new User();
            usuario.setId(UUID.randomUUID().toString());
            usuario.setNombre(name.getText().toString().toLowerCase());
            usuario.setCorreo(email.getText().toString().toLowerCase());
            usuario.setEdad(Integer.parseInt(age.getText().toString()));
            usuario.setGender(gender.getSelectedItem().toString());
            usuario.setUserName(userName.getText().toString().toLowerCase());
            usuario.setPassword(password.getText().toString());

            boolean verif = isRegistered(usuario);

            if(!verif){

                try {
                    conn.registrarUser(usuario);
                }catch (Exception E){
                    String problema = E.getMessage();
                    Toast.makeText(this, problema, Toast.LENGTH_SHORT).show();
                }

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

                Toast.makeText(this, "Ya estás registrado", Toast.LENGTH_SHORT).show();

            }// else
        }// else
    }// method


    public boolean isRegistered(User usuario){

        List<User> lista = conn.getUsers(getApplicationContext(), usuario);

        if (lista.isEmpty()){
            return false;
        }else{
            for(User list : lista){
                if(usuario.getUserName() == list.getUserName() &&
                        usuario.getPassword() == list.getPassword() &&
                        usuario.getNombre() == list.getNombre()){

                    return true;

                }
            }
        }
        return false;
    }

    private void regresar(View view){
        Intent regresar = new Intent(this, InicioSesion.class);
        startActivity(regresar);
        finish();
    }// method

}// class