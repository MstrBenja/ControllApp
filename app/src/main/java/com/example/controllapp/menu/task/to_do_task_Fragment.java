package com.example.controllapp.menu.task;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.controllapp.R;

public class to_do_task_Fragment extends Fragment {
    private Button agregarTarea;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_to_do_task_, container, false);

        LinearLayout ly = (LinearLayout) rootView.findViewById(R.id.padre);
        agregarTarea = (Button) rootView.findViewById(R.id.agregarTarea);

        agregarInfo(rootView, ly);

        return rootView;

    }

    public void agregarInfo(View view, LinearLayout ly){

        agregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView texto = new TextView(to_do_task_Fragment.super.getContext());
                texto.setText(("Si funciona XD"));
                ly.addView(texto);
            }
        });
    }
}