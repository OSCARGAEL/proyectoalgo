package com.mycompany.practica3algodicegame;

import java.util.ArrayList;
import java.util.Random;

// Maneja el tablero usando nodos y una estructura auxiliar ArrayList
public class Tablero extends Historial {

    private int filas;
    private int columnas;
    private ArrayList<Node> nodosDelTablero;
    private boolean tableroConectado;

    // Construye un tablero con filas y columnas
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.nodosDelTablero = new ArrayList<>();
        this.tableroConectado = false;
    }

    // Inicializa el tablero con nodos aleatorios
    public void inicializarTableroConValoresAleatorios() {
        Random aleatorio = new Random();
        nodosDelTablero = new ArrayList<>();
        tableroConectado = false;

        for (int i = 0; i < filas * columnas; i++) {
            Node nuevoNodo = new Node(aleatorio.nextInt(9) + 1);
            nodosDelTablero.add(nuevoNodo);
        }

        conectarVecinosDelTablero();
    }

    // Conecta todos los nodos activos del tablero
    public void conectarVecinosDelTablero() {
        ArrayList<Node> nodosEliminados = obtenerNodosEliminadosActuales();

        for (Node nodo : nodosDelTablero) {
            nodo.delete();
        }

        for (int indice = 0; indice < nodosDelTablero.size(); indice++) {
            Node nodoActual = nodosDelTablero.get(indice);

            if (nodosEliminados.contains(nodoActual)) {
                continue;
            }

            int fila = obtenerFilaPorIndice(indice);
            int columna = obtenerColumnaPorIndice(indice);

            nodoActual.setUp(obtenerNodoActivoEnDireccion(fila, columna, -1, 0, nodosEliminados));
            nodoActual.setDown(obtenerNodoActivoEnDireccion(fila, columna, 1, 0, nodosEliminados));
            nodoActual.setLeft(obtenerNodoActivoEnDireccion(fila, columna, 0, -1, nodosEliminados));
            nodoActual.setRight(obtenerNodoActivoEnDireccion(fila, columna, 0, 1, nodosEliminados));
            nodoActual.setUpLeft(obtenerNodoActivoEnDireccion(fila, columna, -1, -1, nodosEliminados));
            nodoActual.setUpRight(obtenerNodoActivoEnDireccion(fila, columna, -1, 1, nodosEliminados));
            nodoActual.setDownLeft(obtenerNodoActivoEnDireccion(fila, columna, 1, -1, nodosEliminados));
            nodoActual.setDownRight(obtenerNodoActivoEnDireccion(fila, columna, 1, 1, nodosEliminados));
        }

        tableroConectado = true;
    }

    // Obtiene los nodos eliminados usando getNeighbors().isEmpty()
    private ArrayList<Node> obtenerNodosEliminadosActuales() {
        ArrayList<Node> nodosEliminados = new ArrayList<>();

        if (!tableroConectado) {
            return nodosEliminados;
        }

        for (Node nodo : nodosDelTablero) {
            if (nodo.getNeighbors().isEmpty()) {
                nodosEliminados.add(nodo);
            }
        }

        return nodosEliminados;
    }

    // Obtiene el siguiente nodo activo en una direccion
    private Node obtenerNodoActivoEnDireccion(int filaInicial, int columnaInicial, int pasoFila, int pasoColumna, ArrayList<Node> nodosEliminados) {
        int filaActual = filaInicial + pasoFila;
        int columnaActual = columnaInicial + pasoColumna;

        while (filaActual >= 0 && filaActual < filas && columnaActual >= 0 && columnaActual < columnas) {
            Node nodo = obtenerNodoPorPosicion(filaActual, columnaActual);

            if (nodo != null && !nodosEliminados.contains(nodo)) {
                return nodo;
            }

            filaActual += pasoFila;
            columnaActual += pasoColumna;
        }

        return null;
    }

    // Elimina visual y logicamente un nodo del tablero
    public void eliminarNodoDelTablero(Node nodo) {
        if (nodo != null) {
            nodo.delete();
            conectarVecinosDelTablero();
        }
    }

    // Reactiva nodos reconstruyendo sus conexiones
    public void reactivarNodoDelTablero(Node nodo) {
        if (nodo != null) {
            conectarVecinosDelTablero();
        }
    }

    // Verifica si un nodo esta eliminado
    public boolean verificarSiNodoEstaEliminado(Node nodo) {
        if (nodo == null) {
            return true;
        }

        return tableroConectado && nodo.getNeighbors().isEmpty();
    }

    // Obtiene un nodo por fila y columna
    public Node obtenerNodoPorPosicion(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return null;
        }

        int indice = fila * columnas + columna;

        if (indice < 0 || indice >= nodosDelTablero.size()) {
            return null;
        }

        return nodosDelTablero.get(indice);
    }

    // Obtiene el indice de un nodo
    public int obtenerIndiceNodo(Node nodo) {
        return nodosDelTablero.indexOf(nodo);
    }

    // Obtiene la fila segun un indice
    public int obtenerFilaPorIndice(int indice) {
        return indice / columnas;
    }

    // Obtiene la columna segun un indice
    public int obtenerColumnaPorIndice(int indice) {
        return indice % columnas;
    }

    // Obtiene la fila de un nodo
    public int obtenerFilaDeNodo(Node nodo) {
        return obtenerFilaPorIndice(obtenerIndiceNodo(nodo));
    }

    // Obtiene la columna de un nodo
    public int obtenerColumnaDeNodo(Node nodo) {
        return obtenerColumnaPorIndice(obtenerIndiceNodo(nodo));
    }

    // Reactiva dos nodos eliminados reconstruyendo el tablero sin marcarlos como eliminados
    public void reactivarNodosDelTablero(Node nodoUno, Node nodoDos) {
        ArrayList<Node> nodosEliminados = obtenerNodosEliminadosActuales();

        nodosEliminados.remove(nodoUno);
        nodosEliminados.remove(nodoDos);

        for (Node nodo : nodosDelTablero) {
            nodo.delete();
        }

        for (int indice = 0; indice < nodosDelTablero.size(); indice++) {
            Node nodoActual = nodosDelTablero.get(indice);

            if (nodosEliminados.contains(nodoActual)) {
                continue;
            }

            int fila = obtenerFilaPorIndice(indice);
            int columna = obtenerColumnaPorIndice(indice);

            nodoActual.setUp(obtenerNodoActivoEnDireccion(fila, columna, -1, 0, nodosEliminados));
            nodoActual.setDown(obtenerNodoActivoEnDireccion(fila, columna, 1, 0, nodosEliminados));
            nodoActual.setLeft(obtenerNodoActivoEnDireccion(fila, columna, 0, -1, nodosEliminados));
            nodoActual.setRight(obtenerNodoActivoEnDireccion(fila, columna, 0, 1, nodosEliminados));
            nodoActual.setUpLeft(obtenerNodoActivoEnDireccion(fila, columna, -1, -1, nodosEliminados));
            nodoActual.setUpRight(obtenerNodoActivoEnDireccion(fila, columna, -1, 1, nodosEliminados));
            nodoActual.setDownLeft(obtenerNodoActivoEnDireccion(fila, columna, 1, -1, nodosEliminados));
            nodoActual.setDownRight(obtenerNodoActivoEnDireccion(fila, columna, 1, 1, nodosEliminados));
        }

        tableroConectado = true;
    }

    // Obtiene las filas
    public int obtenerFilasTablero() {
        return filas;
    }

    // Obtiene las columnas
    public int obtenerColumnasTablero() {
        return columnas;
    }

    // Obtiene los nodos del tablero
    public ArrayList<Node> obtenerNodosDelTablero() {
        return nodosDelTablero;
    }
}