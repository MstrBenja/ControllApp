package com.example.controllapp.menu.eventos;

import com.google.firebase.annotations.concurrent.Background;

public class Events {

    private String texto;
    private boolean activo;
    private String background;

    public Events(String texto, String background){
        this.texto = texto;
        this.background = background;
        this.activo = true;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean estaActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
