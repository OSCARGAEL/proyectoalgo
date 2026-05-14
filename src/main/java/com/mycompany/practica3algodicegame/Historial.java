package com.mycompany.practica3algodicegame;

import java.util.ArrayList;

// Guarda los movimientos realizados en el juego
public class Historial {

        private ArrayList<Movimiento> listaMovimientos;

        // Construye un historial vacio
        public Historial() {
            listaMovimientos = new ArrayList<>();
        }

        // Agrega un movimiento al historial
        public void agregarMovimientoAlHistorial(Movimiento movimiento) {
            if (movimiento != null) {
                listaMovimientos.add(movimiento);
            }
        }

        // Obtiene el ultimo movimiento guardado
        public Movimiento obtenerUltimoMovimientoGuardado() {
            if (listaMovimientos.isEmpty()) {
                return null;
            }

            return listaMovimientos.get(listaMovimientos.size() - 1);
        }

        // Elimina el ultimo movimiento guardado
        public void eliminarUltimoMovimientoGuardado() {
            if (!listaMovimientos.isEmpty()) {
                listaMovimientos.remove(listaMovimientos.size() - 1);
            }
        }

        // Verifica si el historial esta vacio
        public boolean verificarHistorialVacio() {
            return listaMovimientos.isEmpty();
        }
}
