package com.mycompany.practica3algodicegame;

// Guarda dos casillas que forman una pista valida
public class Pista {

        private Node nodoUno;
        private Node nodoDos;

        // Construye una pista con dos nodos
        public Pista(Node nodoUno, Node nodoDos) {
            this.nodoUno = nodoUno;
            this.nodoDos = nodoDos;
        }

        // Obtiene el primer nodo
        public Node obtenerNodoUno() {
            return nodoUno;
        }

        // Obtiene el segundo nodo
        public Node obtenerNodoDos() {
            return nodoDos;
        }
}
