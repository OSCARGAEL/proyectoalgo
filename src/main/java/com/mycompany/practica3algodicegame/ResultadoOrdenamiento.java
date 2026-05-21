package com.mycompany.practica3algodicegame;

public class ResultadoOrdenamiento {

    private String metodo;
    private String columna;
    private long tiempoNanosegundos;

    public ResultadoOrdenamiento(String metodo, String columna, long tiempoNanosegundos) {
        this.metodo = metodo;
        this.columna = columna;
        this.tiempoNanosegundos = tiempoNanosegundos;
    }

    // Regresa el nombre del metodo usado.
    public String getMetodo() {
        return metodo;
    }

    // Regresa la columna ordenada.
    public String getColumna() {
        return columna;
    }

    // Regresa el tiempo en nanosegundos.
    public long getTiempoNanosegundos() {
        return tiempoNanosegundos;
    }

    // Regresa el tiempo en milisegundos.
    public double getTiempoMilisegundos() {
        return tiempoNanosegundos / 1000000.0;
    }
}