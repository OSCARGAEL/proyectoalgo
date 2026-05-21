package com.mycompany.practica3algodicegame;

import java.util.ArrayList;

public class SimuladorOrdenamientos {

    private ArrayList<Videojuego> videojuegos;
    private LectorCSV lectorCSV;
    private ComparadorTiempos comparadorTiempos;

    public SimuladorOrdenamientos() {
        videojuegos = new ArrayList<>();
        lectorCSV = new LectorCSV();
        comparadorTiempos = new ComparadorTiempos();
    }

    // Carga los videojuegos desde el archivo CSV.
    public void cargarVideojuegos() {
        videojuegos = lectorCSV.leerVideojuegos("games.csv");
    }

    // Regresa la lista original de videojuegos.
    public ArrayList<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    // Ordena los videojuegos usando el metodo y columna seleccionados.
    public ArrayList<Videojuego> ordenarVideojuegos(String metodo, String columna) {
        return comparadorTiempos.ordenarLista(videojuegos, metodo, columna);
    }

    // Mide el tiempo de un metodo de ordenamiento.
    public ResultadoOrdenamiento medirOrdenamiento(String metodo, String columna) {
        return comparadorTiempos.medirTiempo(videojuegos, metodo, columna);
    }

    // Compara todos los metodos de ordenamiento.
    public ArrayList<ResultadoOrdenamiento> compararTodos(String columna) {
        return comparadorTiempos.compararTodos(videojuegos, columna);
    }
}