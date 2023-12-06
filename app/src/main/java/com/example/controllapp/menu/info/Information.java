package com.example.controllapp.menu.info;

import static com.example.controllapp.DB.BD.getDatabaseInstance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.controllapp.DB.DAO;
import com.example.controllapp.DB.Events;
import com.example.controllapp.DB.Tareas;
import com.example.controllapp.DB.User;
import com.example.controllapp.R;
import com.example.controllapp.menu.Menu;
import com.example.controllapp.menu.eventos.infoEventos;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Information extends AppCompatActivity {

    private TextView infoNombre, infoApp;
    private DAO dao;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        infoNombre = (TextView) findViewById(R.id.infoNombreUsuario);
        infoApp = (TextView) findViewById(R.id.infoApp);

        dbReference = getDatabaseInstance(this);
        dao = new DAO(dbReference);

    }// onCreate

    public void agregarInformación(){

        User usuario = dao.getUsuarioConectado();

        infoNombre.setText("Información de "+usuario.getUserName()+"\n"+
                "Mejor conocido como: "+usuario.getNombre()+"\n" +
                "Genero: "+usuario.getGender()+"\n" +
                "Edad: "+usuario.getEdad()+"\n" +
                "Correo: "+usuario.getCorreo()+"\n" +
                "(Tu ID es este:): "+usuario.getId());

        List<Tareas> listaTareas = usuario.getTareas();
        List<Events> listaEventos = usuario.getEventos();

        int i = 0;
        int complete = 0;
        int incomplete = 0;
        int eventos = 0;
        for(Tareas lista: listaTareas){
            i++;
            if(lista.estaActivo()){
                incomplete++;
            }else{
                complete++;
            }
        }

        for(Events lista: listaEventos){
            eventos++;
        }

        infoApp.setText("Cantidad de tareas agregadas: "+i+"\n" +
                "Tareas completadas :"+complete+"\n" +
                "Tareas incompletas: "+incomplete+"\n"+"\n" +
                "Eventos creados :"+eventos);
    }

    public void aMenuFromInfo(View v){
        Intent menu = new Intent(this, Menu.class);
        startActivity(menu);
    }// method

    public void infoEvents(){
        Intent info = new Intent(this, infoEventos.class);
        startActivity(info);
    }
}// class