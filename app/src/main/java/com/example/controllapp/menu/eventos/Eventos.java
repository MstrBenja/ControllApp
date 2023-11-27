package com.example.controllapp.menu.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.controllapp.R;
import com.example.controllapp.menu.Menu;

import java.util.ArrayList;

public class Eventos extends AppCompatActivity {

    private LinearLayout primerLayout, segundoLayout, tercerLayout, cuartoLayout, progresoEvento;
    private TableLayout tabla;
    private TableRow primeraFila, segundaFila, terceraFila, cuartaFila;
    private Button agregar, evento;
    private View estructura_evento;
    public static ArrayList<EventsInfo> listaEventos;
    public String textoBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        estructura_evento = getLayoutInflater().inflate(R.layout.event_estructure, null);

        // Linear Layouts
        primerLayout = (LinearLayout) findViewById(R.id.firstParent);
        segundoLayout = (LinearLayout) findViewById(R.id.secondParent);
        tercerLayout = (LinearLayout) findViewById(R.id.thirdParent);
        cuartoLayout = (LinearLayout) findViewById(R.id.fourthParent);
        progresoEvento = (LinearLayout) findViewById(R.id.showProgressUI);

        // Table Rows
        primeraFila = (TableRow) findViewById(R.id.firstTableRow);
        segundaFila = (TableRow) findViewById(R.id.secondTableRow);
        terceraFila = (TableRow) findViewById(R.id.thirdTableRow);
        cuartaFila = (TableRow) findViewById(R.id.fourthTableRow);
        tabla = (TableLayout) findViewById(R.id.tabla);

        // Button
        agregar = (Button) findViewById(R.id.agregar);


        // open activity to add event.
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarEvento(v);
            }
        });

    }// onCreate


    public void eventoGuardados(View estructura_evento){

        for (EventsInfo lista: listaEventos){

            evento = (Button) estructura_evento.findViewById(R.id.evento);

            String texto = evento.getText().toString();

            evento.setText(texto);

            boolean activo = lista.estaActivo();

            if(activo){
                int fila = verificarFila();

                switch (fila){
                    case 1:
                        primeraFila.addView(evento);
                        break;
                    case 2:
                        segundaFila.addView(evento);
                        break;
                    case 3:
                        terceraFila.addView(evento);
                        break;
                    case 4:
                        cuartaFila.addView(evento);
                        break;
                }
            }

        }
    }

    public int verificarFila(){
        if (primeraFila.getChildCount() <= 4){
           return 1;
        }else if (segundaFila.getChildCount() <= 4){
            return 2;
        }else if (terceraFila.getChildCount() <= 4){
            return 3;
        }else {
            return 4;
        }
    }

    public void eventoPresionado(View view){

    }

    public void aMenuFromEvents(View v){
        Intent menu = new Intent(this, Menu.class);
        startActivity(menu);
    }// method

    public void agregarEvento(View view){

        Intent intent = new Intent(this, add_Event.class);
        startActivity(intent);
        finish();
    }

}// class