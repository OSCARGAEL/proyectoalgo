package com.mycompany.practica3algodicegame;

import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;

public class GrafoDirigidoAciclico {
    private int vertices;
    private LinkedList<Integer>[] lista;

    public GrafoDirigidoAciclico(int n) {
        vertices = n;
        lista = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            lista[i] = new LinkedList<>();
        }
    }

    private void validarVertice(int i) {
        if (i < 0 || i >= vertices) {
            throw new IllegalArgumentException("Vertice fuera de rango");
        }
    }

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

    public int gradoDeSalida(int i) {
        validarVertice(i);

        return lista[i].size();
    }

    public int cuantasAristasHay() {
        int total = 0;

        for (int i = 0; i < vertices; i++) {
            total += lista[i].size();
        }

        return total;
    }

    public boolean adyacente(int i, int j) {
        validarVertice(i);
        validarVertice(j);

        return lista[i].contains(j);
    }

    public boolean conectados(int i, int j) {
        validarVertice(i);
        validarVertice(j);

        boolean[] visitado = new boolean[vertices];
        Queue<Integer> cola = new LinkedList<>();

        cola.add(i);
        visitado[i] = true;

        while (!cola.isEmpty()) {
            int actual = cola.poll();

            if (actual == j) {
                return true;
            }

            for (int vecino : lista[actual]) {
                if (!visitado[vecino]) {
                    visitado[vecino] = true;
                    cola.add(vecino);
                }
            }
        }

        return false;
    }

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

    public void eliminarAristas() {
        for (int i = 0; i < vertices; i++) {
            lista[i].clear();
        }
    }

    public boolean tieneCiclos() {
        int[] grados = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            grados[i] = gradoDeEntrada(i);
        }

        Queue<Integer> cola = new LinkedList<>();

        for (int i = 0; i < vertices; i++) {
            if (grados[i] == 0) {
                cola.add(i);
            }
        }

        int visitados = 0;

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            visitados++;

            for (int vecino : lista[actual]) {
                grados[vecino]--;

                if (grados[vecino] == 0) {
                    cola.add(vecino);
                }
            }
        }

        return visitados != vertices;
    }

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

    public String mostrarEstructura() {
        String texto = "Matriz de adyacencia:\n\n";

        texto += "   ";

        for (int i = 0; i < vertices; i++) {
            texto += i + " ";
        }

        texto += "\n";

        for (int i = 0; i < vertices; i++) {
            texto += i + ": ";

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