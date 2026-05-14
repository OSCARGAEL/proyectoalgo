package com.mycompany.practica3algodicegame;

// Guarda una jugada realizada para poder deshacerla
public class Movimiento {

        private Node nodoUno;
        private Node nodoDos;

        // Construye un movimiento con dos nodos
        public Movimiento(Node nodoUno, Node nodoDos) {
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
