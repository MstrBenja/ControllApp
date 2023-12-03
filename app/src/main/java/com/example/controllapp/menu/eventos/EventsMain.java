package com.example.controllapp.menu.eventos;

import static com.example.controllapp.inicioUsuario.InicioSesion.listaEventos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controllapp.R;
import com.example.controllapp.menu.Menu;

import java.util.ArrayList;

public class EventsMain extends AppCompatActivity {

    private LinearLayout primerLayout, segundoLayout, tercerLayout, cuartoLayout, progresoEvento;
    private TableLayout tabla;
    private TableRow primeraFila, segundaFila, terceraFila, cuartaFila;
    private Button agregar, evento;
    private View estructura_evento;
    public String textoBoton;
    private TextView textoMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

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

        textoMostrar = (TextView) findViewById(R.id.textoMostrar);

        // open activity to add event.
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarEvento(v);
            }
        });

        try {
            eventoGuardado();
        }catch (Exception E){
            Toast.makeText(this, E.toString(), Toast.LENGTH_SHORT).show();
        }


        //eventoPresionado();

    }// onCreate


    public void eventoGuardado(){

        if(listaEventos.isEmpty()){
            Toast.makeText(this, "No hay nada", Toast.LENGTH_SHORT).show();
        }else{
            for (Events lista: listaEventos){

                GradientDrawable fondoBoton = new GradientDrawable();
                fondoBoton.setShape(R.drawable.event_design);

                estructura_evento = getLayoutInflater().inflate(R.layout.event_estructure, null);

                evento = (Button) estructura_evento.findViewById(R.id.evento);

                String texto = lista.getTexto().toString();
                String color = "";

                switch (lista.getBackground()) {
                    case "Rojo":
                        color = "#DF1313";
                        fondoBoton.setColor(Color.parseColor(color));
                        break;
                    case "Amarillo":
                        color = "#FFFF00";
                        fondoBoton.setColor(Color.parseColor(color));
                        break;
                    case "Verde":
                        color = "#13C116";
                        fondoBoton.setColor(Color.parseColor(color));
                        break;
                    case "Negro":
                        color = "#FF000000";
                        fondoBoton.setColor(Color.parseColor(color));
                        break;
                    case "Azul":
                        color = "#0F45E3";
                        fondoBoton.setColor(Color.parseColor(color));
                        break;
                    case "Morado":
                        color = "#6813DF";
                        fondoBoton.setColor(Color.parseColor(color));
                        break;
                    case "Naranjo":
                        color = "#B1691B";
                        fondoBoton.setColor(Color.parseColor(color));
                        break;
                    case "Rosado":
                        color = "#FFC0CB";
                        fondoBoton.setColor(Color.parseColor(color));
                        break;
                    case "Celeste":
                        color = "#0EC6D6";
                        fondoBoton.setColor(Color.parseColor(color));
                        break;
                }

                evento.setText(texto);
                evento.setBackground(fondoBoton);
                evento.setTextColor(Color.WHITE);

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

    public void eventoPresionado(){

        estructura_evento = getLayoutInflater().inflate(R.layout.event_estructure, null);

        Button boton = estructura_evento.findViewById(R.id.evento);

        evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String texto =  boton.getText().toString();
                textoMostrar.setText(texto);

            }
        });

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