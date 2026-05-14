package com.mycompany.practica3algodicegame;

import java.util.ArrayList;

// Contiene toda la logica principal del juego Number Match
public class Juego extends Node {

    private Tablero tablero;
    private Historial historial;
    private int totalConcordanciasEncontradas;

    // Construye un juego con las dimensiones indicadas
    public Juego(int filas, int columnas) {
        tablero = new Tablero(filas, columnas);
        historial = new Historial();
        totalConcordanciasEncontradas = 0;
    }

    // Inicia una nueva partida
    public void iniciarNuevaPartida() {
        tablero.inicializarTableroConValoresAleatorios();
        historial = new Historial();
        totalConcordanciasEncontradas = 0;
    }

    // Obtiene el tablero actual
    public Tablero obtenerTableroJuego() {
        return tablero;
    }

    // Obtiene el total de concordancias encontradas
    public int obtenerTotalConcordanciasEncontradas() {
        return totalConcordanciasEncontradas;
    }

    // Verifica si dos nodos pueden formar un movimiento valido
    public boolean verificarSiMovimientoEsValido(Node nodoUno, Node nodoDos) {

        if (nodoUno == null || nodoDos == null) {
            return false;
        }

        if (tablero.verificarSiNodoEstaEliminado(nodoUno) ||
                tablero.verificarSiNodoEstaEliminado(nodoDos)) {
            return false;
        }

        if (nodoUno == nodoDos) {
            return false;
        }

        if (nodoUno.compareTo(nodoDos) != 0) {
            return false;
        }

        if (nodoUno.isNeighbor(nodoDos)) {
            return true;
        }

        if (verificarSiEstanEnMismaFilaConVaciosEntreEllos(nodoUno, nodoDos)) {
            return true;
        }

        if (verificarSiEstanEnMismaColumnaConVaciosEntreEllos(nodoUno, nodoDos)) {
            return true;
        }

        if (verificarSiEstanEnDiagonalConVaciosEntreEllos(nodoUno, nodoDos)) {
            return true;
        }

        if (verificarSiEstanEntreFinalEInicioDeRenglonesConVacios(nodoUno, nodoDos)) {
            return true;
        }

        return false;
    }

    // Verifica si dos nodos estan en la misma fila con vacios entre ellos
    private boolean verificarSiEstanEnMismaFilaConVaciosEntreEllos(Node nodoUno, Node nodoDos) {

        int filaUno = tablero.obtenerFilaDeNodo(nodoUno);
        int filaDos = tablero.obtenerFilaDeNodo(nodoDos);

        if (filaUno != filaDos) {
            return false;
        }

        int columnaUno = tablero.obtenerColumnaDeNodo(nodoUno);
        int columnaDos = tablero.obtenerColumnaDeNodo(nodoDos);

        int columnaInicio = Math.min(columnaUno, columnaDos) + 1;
        int columnaFin = Math.max(columnaUno, columnaDos);

        for (int columnaActual = columnaInicio; columnaActual < columnaFin; columnaActual++) {

            Node nodoIntermedio = tablero.obtenerNodoPorPosicion(filaUno, columnaActual);

            if (nodoIntermedio != null &&
                    !tablero.verificarSiNodoEstaEliminado(nodoIntermedio)) {

                return false;
            }
        }

        return true;
    }

    // Verifica si dos nodos estan en la misma columna con vacios entre ellos
    private boolean verificarSiEstanEnMismaColumnaConVaciosEntreEllos(Node nodoUno, Node nodoDos) {

        int columnaUno = tablero.obtenerColumnaDeNodo(nodoUno);
        int columnaDos = tablero.obtenerColumnaDeNodo(nodoDos);

        if (columnaUno != columnaDos) {
            return false;
        }

        int filaUno = tablero.obtenerFilaDeNodo(nodoUno);
        int filaDos = tablero.obtenerFilaDeNodo(nodoDos);

        int filaInicio = Math.min(filaUno, filaDos) + 1;
        int filaFin = Math.max(filaUno, filaDos);

        for (int filaActual = filaInicio; filaActual < filaFin; filaActual++) {

            Node nodoIntermedio = tablero.obtenerNodoPorPosicion(filaActual, columnaUno);

            if (nodoIntermedio != null &&
                    !tablero.verificarSiNodoEstaEliminado(nodoIntermedio)) {

                return false;
            }
        }

        return true;
    }

    // Verifica si dos nodos estan en diagonal con vacios entre ellos
    private boolean verificarSiEstanEnDiagonalConVaciosEntreEllos(Node nodoUno, Node nodoDos) {

        int filaUno = tablero.obtenerFilaDeNodo(nodoUno);
        int filaDos = tablero.obtenerFilaDeNodo(nodoDos);

        int columnaUno = tablero.obtenerColumnaDeNodo(nodoUno);
        int columnaDos = tablero.obtenerColumnaDeNodo(nodoDos);

        int diferenciaFilas = Math.abs(filaUno - filaDos);
        int diferenciaColumnas = Math.abs(columnaUno - columnaDos);

        if (diferenciaFilas != diferenciaColumnas) {
            return false;
        }

        int pasoFila;

        if (filaDos > filaUno) {
            pasoFila = 1;
        } else {
            pasoFila = -1;
        }

        int pasoColumna;

        if (columnaDos > columnaUno) {
            pasoColumna = 1;
        } else {
            pasoColumna = -1;
        }

        int filaActual = filaUno + pasoFila;
        int columnaActual = columnaUno + pasoColumna;

        while (filaActual != filaDos && columnaActual != columnaDos) {

            Node nodoIntermedio = tablero.obtenerNodoPorPosicion(filaActual, columnaActual);

            if (nodoIntermedio != null &&
                    !tablero.verificarSiNodoEstaEliminado(nodoIntermedio)) {

                return false;
            }

            filaActual += pasoFila;
            columnaActual += pasoColumna;
        }

        return true;
    }

    // Verifica si dos nodos estan entre final e inicio de renglones
    private boolean verificarSiEstanEntreFinalEInicioDeRenglonesConVacios(Node nodoUno, Node nodoDos) {

        int filaUno = tablero.obtenerFilaDeNodo(nodoUno);
        int filaDos = tablero.obtenerFilaDeNodo(nodoDos);

        if (filaDos == filaUno + 1) {
            return verificarCaminoEntreRenglones(nodoUno, nodoDos);
        }

        if (filaUno == filaDos + 1) {
            return verificarCaminoEntreRenglones(nodoDos, nodoUno);
        }

        return false;
    }

    // Verifica el camino entre renglones consecutivos
    private boolean verificarCaminoEntreRenglones(Node nodoInicio, Node nodoSiguiente) {

        int filaInicio = tablero.obtenerFilaDeNodo(nodoInicio);
        int filaSiguiente = tablero.obtenerFilaDeNodo(nodoSiguiente);

        int columnaInicio = tablero.obtenerColumnaDeNodo(nodoInicio);
        int columnaSiguiente = tablero.obtenerColumnaDeNodo(nodoSiguiente);

        for (int columnaActual = columnaInicio + 1;
             columnaActual < tablero.obtenerColumnasTablero();
             columnaActual++) {

            Node nodoIntermedio =
                    tablero.obtenerNodoPorPosicion(filaInicio, columnaActual);

            if (nodoIntermedio != null &&
                    !tablero.verificarSiNodoEstaEliminado(nodoIntermedio)) {

                return false;
            }
        }

        for (int columnaActual = 0;
             columnaActual < columnaSiguiente;
             columnaActual++) {

            Node nodoIntermedio =
                    tablero.obtenerNodoPorPosicion(filaSiguiente, columnaActual);

            if (nodoIntermedio != null &&
                    !tablero.verificarSiNodoEstaEliminado(nodoIntermedio)) {

                return false;
            }
        }

        return true;
    }

    // Realiza un movimiento entre dos nodos
    public boolean realizarMovimientoEntreNodos(Node nodoUno, Node nodoDos) {

        if (!verificarSiMovimientoEsValido(nodoUno, nodoDos)) {
            return false;
        }

        Movimiento nuevoMovimiento =
                new Movimiento(nodoUno, nodoDos);

        historial.agregarMovimientoAlHistorial(nuevoMovimiento);

        tablero.eliminarNodoDelTablero(nodoUno);
        tablero.eliminarNodoDelTablero(nodoDos);

        totalConcordanciasEncontradas++;

        return true;
    }

    // Busca una pista disponible
    public Pista buscarPistaDisponibleEnTablero() {

        ArrayList<Node> nodos =
                tablero.obtenerNodosDelTablero();

        for (Node nodoA : nodos) {

            if (tablero.verificarSiNodoEstaEliminado(nodoA)) {
                continue;
            }

            for (Node nodoB : nodos) {

                if (tablero.verificarSiNodoEstaEliminado(nodoB)) {
                    continue;
                }

                if (verificarSiMovimientoEsValido(nodoA, nodoB)) {
                    return new Pista(nodoA, nodoB);
                }
            }
        }

        return null;
    }

    // Cuenta las concordancias pendientes
    public int contarConcordanciasPendientesEnTablero() {

        int contador = 0;

        ArrayList<Node> nodos =
                tablero.obtenerNodosDelTablero();

        for (Node nodoA : nodos) {

            if (tablero.verificarSiNodoEstaEliminado(nodoA)) {
                continue;
            }

            for (Node nodoB : nodos) {

                if (tablero.verificarSiNodoEstaEliminado(nodoB)) {
                    continue;
                }

                if (verificarSiMovimientoEsValido(nodoA, nodoB)) {
                    contador++;
                }
            }
        }

        return contador / 2;
    }

    // Verifica si ya no hay movimientos disponibles
    public boolean verificarSiYaNoHayMovimientosDisponibles() {
        return buscarPistaDisponibleEnTablero() == null;
    }

    // Deshace el ultimo movimiento
    public boolean deshacerUltimoMovimientoRealizado() {

        if (historial.verificarHistorialVacio()) {
            return false;
        }

        Movimiento ultimoMovimiento =
                historial.obtenerUltimoMovimientoGuardado();

        if (ultimoMovimiento == null) {
            return false;
        }

        tablero.reactivarNodosDelTablero(
                ultimoMovimiento.obtenerNodoUno(),
                ultimoMovimiento.obtenerNodoDos()
        );

        historial.eliminarUltimoMovimientoGuardado();

        if (totalConcordanciasEncontradas > 0) {
            totalConcordanciasEncontradas--;
        }

        return true;
    }
}