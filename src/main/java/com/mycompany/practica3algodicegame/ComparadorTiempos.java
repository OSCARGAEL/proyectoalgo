package com.mycompany.practica3algodicegame;

import java.util.ArrayList;
import java.util.Comparator;

public class ComparadorTiempos {

    private OrdenadorVideojuegos ordenador;

    public ComparadorTiempos() {
        ordenador = new OrdenadorVideojuegos();
    }

    // Mide el tiempo de un metodo de ordenamiento.
    public ResultadoOrdenamiento medirTiempo(ArrayList<Videojuego> listaOriginal,
                                             String metodo, String columna) {

        ArrayList<Videojuego> copia = new ArrayList<>(listaOriginal);
        Comparator<Videojuego> comparador = ordenador.obtenerComparador(columna);

        long inicio = System.nanoTime();

        switch (metodo) {
            case "Seleccion Directa":
                ordenador.seleccionDirecta(copia, comparador);
                break;
            case "Shell Sort":
                ordenador.shellSort(copia, comparador);
                break;
            case "Merge Sort":
                ordenador.mergeSort(copia, comparador);
                break;
            case "Quick Sort":
                ordenador.quickSort(copia, comparador);
                break;
            case "Radix Sort":
                if (ordenador.columnaEsNumerica(columna)) {
                    ordenador.radixSort(copia, columna);
                }
                break;
            case "Arrays.sort":
                ordenador.arraysSort(copia, comparador);
                break;
            case "Arrays.parallelSort":
                ordenador.arraysParallelSort(copia, comparador);
                break;
        }

        long fin = System.nanoTime();
        return new ResultadoOrdenamiento(metodo, columna, fin - inicio);
    }

    // Ordena una copia y la regresa para mostrarla en la tabla.
    public ArrayList<Videojuego> ordenarLista(ArrayList<Videojuego> listaOriginal,
                                              String metodo, String columna) {

        ArrayList<Videojuego> copia = new ArrayList<>(listaOriginal);
        Comparator<Videojuego> comparador = ordenador.obtenerComparador(columna);

        switch (metodo) {
            case "Seleccion Directa":
                ordenador.seleccionDirecta(copia, comparador);
                break;
            case "Shell Sort":
                ordenador.shellSort(copia, comparador);
                break;
            case "Merge Sort":
                ordenador.mergeSort(copia, comparador);
                break;
            case "Quick Sort":
                ordenador.quickSort(copia, comparador);
                break;
            case "Radix Sort":
                if (ordenador.columnaEsNumerica(columna)) {
                    ordenador.radixSort(copia, columna);
                }
                break;
            case "Arrays.sort":
                ordenador.arraysSort(copia, comparador);
                break;
            case "Arrays.parallelSort":
                ordenador.arraysParallelSort(copia, comparador);
                break;
        }

        return copia;
    }

    // Ejecuta todos los metodos de ordenamiento.
    public ArrayList<ResultadoOrdenamiento> compararTodos(ArrayList<Videojuego> listaOriginal,
                                                          String columna) {

        ArrayList<ResultadoOrdenamiento> resultados = new ArrayList<>();

        resultados.add(medirTiempo(listaOriginal, "Seleccion Directa", columna));
        resultados.add(medirTiempo(listaOriginal, "Shell Sort", columna));
        resultados.add(medirTiempo(listaOriginal, "Merge Sort", columna));
        resultados.add(medirTiempo(listaOriginal, "Quick Sort", columna));

        if (ordenador.columnaEsNumerica(columna)) {
            resultados.add(medirTiempo(listaOriginal, "Radix Sort", columna));
        }

        resultados.add(medirTiempo(listaOriginal, "Arrays.sort", columna));
        resultados.add(medirTiempo(listaOriginal, "Arrays.parallelSort", columna));

        return resultados;
    }
}