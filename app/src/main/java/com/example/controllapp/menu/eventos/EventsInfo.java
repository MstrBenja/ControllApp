package com.example.controllapp.menu.eventos;

public class EventsInfo {

    private String texto;
    private boolean activo;

    public EventsInfo(String texto){
        this.texto = texto;
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
}
