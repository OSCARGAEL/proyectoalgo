package com.mycompany.practica3algodicegame;

import java.util.ArrayList;
import java.util.PriorityQueue;

// Clase que representa un grafo dirigido aciclico
public class GrafoDirigidoAciclico {

    // Cantidad de vertices del grafo
    private int vertices;

    // Lista de adyacencia del grafo
    private ArrayList<Integer>[] lista;

    // Constructor del grafo
    public GrafoDirigidoAciclico(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("La cantidad de vertices debe ser mayor a cero");
        }

        vertices = n;
        lista = new ArrayList[vertices];

        for (int i = 0; i < vertices; i++) {
            lista[i] = new ArrayList<>();
        }
    }

    // Verifica que el vertice este dentro del rango permitido
    private void validarVertice(int i) {
        if (i < 0 || i >= vertices) {
            throw new IllegalArgumentException("Vertice fuera de rango");
        }
    }

    // Calcula el grado de entrada de un vertice
    public int gradoDeEntrada(int i) {
        validarVertice(i);

        int grado = 0;

        for (int v = 0; v < vertices; v++) {
            if (lista[v].contains(i)) {
                grado++;
            }
        }

        return grado;
    }

    // Calcula el grado de salida de un vertice
    public int gradoDeSalida(int i) {
        validarVertice(i);

        return lista[i].size();
    }

    // Cuenta la cantidad total de aristas del grafo
    public int cuantasAristasHay() {
        int total = 0;

        for (int i = 0; i < vertices; i++) {
            total += lista[i].size();
        }

        return total;
    }

    // Verifica si existe una arista entre dos vertices
    public boolean adyacente(int i, int j) {
        validarVertice(i);
        validarVertice(j);

        return lista[i].contains(j);
    }

    // Verifica si existe un camino entre dos vertices usando BFS
    public boolean conectados(int i, int j) {
        validarVertice(i);
        validarVertice(j);

        boolean[] visitado = new boolean[vertices];

        ColaSimple<Integer> cola = new ColaSimple<>(vertices);

        cola.insertar(i);
        visitado[i] = true;

        while (!cola.estaVacia()) {
            Integer actual = cola.eliminar();

            if (actual == j) {
                return true;
            }

            for (int vecino : lista[actual]) {
                if (!visitado[vecino]) {
                    visitado[vecino] = true;
                    cola.insertar(vecino);
                }
            }
        }

        return false;
    }

    // Inserta una nueva arista evitando ciclos y duplicados
    public boolean insertarArista(int i, int j) {
        validarVertice(i);
        validarVertice(j);

        if (i == j) {
            return false;
        }

        if (adyacente(i, j)) {
            return false;
        }

        if (conectados(j, i)) {
            return false;
        }

        lista[i].add(j);

        return true;
    }

    // Elimina todas las aristas del grafo
    public void eliminarAristas() {
        for (int i = 0; i < vertices; i++) {
            lista[i].clear();
        }
    }

    // Metodo adicional con el nombre solicitado en el PDF
    public void elimiarAristas() {
        eliminarAristas();
    }

    // Detecta si el grafo contiene ciclos
    public boolean tieneCiclos() {
        int[] grados = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            grados[i] = gradoDeEntrada(i);
        }

        ColaSimple<Integer> cola = new ColaSimple<>(vertices);

        for (int i = 0; i < vertices; i++) {
            if (grados[i] == 0) {
                cola.insertar(i);
            }
        }

        int visitados = 0;

        while (!cola.estaVacia()) {
            Integer actual = cola.eliminar();

            visitados++;

            for (int vecino : lista[actual]) {
                grados[vecino]--;

                if (grados[vecino] == 0) {
                    cola.insertar(vecino);
                }
            }
        }

        return visitados != vertices;
    }

    // Realiza el ordenamiento topologico del grafo
    public String topologicalSort() {
        int[] grados = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            grados[i] = gradoDeEntrada(i);
        }

        PriorityQueue<Integer> cola = new PriorityQueue<>();

        for (int i = 0; i < vertices; i++) {
            if (grados[i] == 0) {
                cola.add(i);
            }
        }

        String resultado = "";
        int visitados = 0;

        while (!cola.isEmpty()) {
            int actual = cola.poll();

            if (!resultado.equals("")) {
                resultado += " - ";
            }

            resultado += actual;

            visitados++;

            for (int vecino : lista[actual]) {
                grados[vecino]--;

                if (grados[vecino] == 0) {
                    cola.add(vecino);
                }
            }
        }

        if (visitados != vertices) {
            return "El grafo tiene ciclos";
        }

        return resultado;
    }

    // Muestra la estructura del grafo como matriz de adyacencia
    public String mostrarEstructura() {
        String texto = "";

        texto += "Matriz de adyacencia:\n\n";
        texto += "    ";

        for (int i = 0; i < vertices; i++) {
            texto += i + " ";
        }

        texto += "\n";

        for (int i = 0; i < vertices; i++) {
            texto += i + " | ";

            for (int j = 0; j < vertices; j++) {
                if (adyacente(i, j)) {
                    texto += "1 ";
                } else {
                    texto += "0 ";
                }
            }

            texto += "\n";
        }

        return texto;
    }
}