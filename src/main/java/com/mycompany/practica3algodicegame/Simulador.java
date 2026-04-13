/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica3algodicegame;

/**
 *
 * @author HP
 */
import java.util.ArrayList;
import java.util.List;

public class Simulador {

    private List<Fila> listaFilas;
    private int turnoActual;
    private int contadorClientes;
    private Dado dado;
    private int turnoMaximo;
    private boolean dadosGirados;
    private int[] valoresDados;

    // Inicializa el simulador.
    public Simulador() {
        listaFilas = new ArrayList<>();
        turnoActual = 0;
        contadorClientes = 0;
        dado = new Dado();
        turnoMaximo = 20;
        dadosGirados = false;
        valoresDados = new int[0];
    }

    // Inicializa toda la simulacion.
    public void inicializarSimulacion(int numeroFilas) {
        listaFilas.clear();
        turnoActual = 0;
        contadorClientes = 0;
        dadosGirados = false;

        for (int i = 1; i <= numeroFilas; i++) {
            listaFilas.add(new Fila("Fila " + i));
        }

        for (Fila fila : listaFilas) {
            for (int i = 0; i < 4; i++) {
                contadorClientes++;
                fila.agregarClienteACola(new Cliente(contadorClientes, turnoActual, false));
            }
        }

        valoresDados = new int[numeroFilas];
    }

    // Gira los dados del turno actual.
    public void girarDados() {
        if (turnoActual >= turnoMaximo) {
            return;
        }

        if (dadosGirados) {
            return;
        }

        turnoActual++;

        for (int i = 0; i < listaFilas.size(); i++) {
            int valor = dado.lanzarDado();
            valoresDados[i] = valor;
            listaFilas.get(i).asignarValorDado(valor);
        }

        dadosGirados = true;
    }

    // Mueve los clientes segun los valores de los dados.
    public void moverSegunDados() {
        if (!dadosGirados) {
            return;
        }

        for (int i = listaFilas.size() - 1; i >= 1; i--) {
            moverEntreFilas(i - 1, i, valoresDados[i]);
        }

        generarNuevos(valoresDados[0]);

        dadosGirados = false;
    }

    // Mueve clientes de una fila origen a una fila destino.
    public void moverEntreFilas(int origen, int destino, int cantidad) {
        Fila filaOrigen = listaFilas.get(origen);
        Fila filaDestino = listaFilas.get(destino);

        int movidos = 0;

        for (int i = 0; i < cantidad; i++) {
            if (filaOrigen.estaColaVacia()) {
                break;
            }

            Cliente cliente = filaOrigen.removerClienteDeCola();

            if (cliente != null) {
                filaDestino.agregarClienteACola(cliente);
                movidos++;
            }
        }

        filaDestino.incrementarClientesProcesados(movidos);
    }

    // Genera clientes nuevos en la fila 1.
    public void generarNuevos(int cantidad) {
        if (listaFilas.isEmpty()) {
            return;
        }

        Fila fila1 = listaFilas.get(0);

        for (int i = 0; i < cantidad; i++) {
            contadorClientes++;
            fila1.agregarClienteACola(new Cliente(contadorClientes, turnoActual, true));
        }
    }

    // Reinicia la simulacion.
    public void reiniciarSimulacion() {
        int numeroFilas = listaFilas.size();
        inicializarSimulacion(numeroFilas);
    }

    // Devuelve la lista de filas.
    public List<Fila> obtenerListaFilas() {
        return listaFilas;
    }

    // Devuelve el turno actual.
    public int obtenerTurnoActual() {
        return turnoActual;
    }

    // Devuelve el turno maximo.
    public int obtenerTurnoMaximo() {
        return turnoMaximo;
    }

    // Indica si los dados ya fueron girados.
    public boolean estanDadosGirados() {
        return dadosGirados;
    }

    // Indica si la simulacion ya termino.
    public boolean simulacionTerminada() {
        return turnoActual >= turnoMaximo && !dadosGirados;
    }

    // Devuelve el total de clientes en el sistema.
    public int obtenerTotalClientesEnSistema() {
        int total = 0;

        for (Fila fila : listaFilas) {
            total += fila.obtenerCantidadClientesEnCola();
        }

        return total;
    }

    // Calcula el porcentaje de actividad del sistema.
    public double calcularActividadSistema() {
        if (turnoActual == 0) {
            return 0;
        }

        int totalProcesados = 0;

        for (Fila fila : listaFilas) {
            totalProcesados += fila.obtenerTotalProcesados();
        }

        double capacidadMaxima = turnoActual * listaFilas.size() * 6.0;

        return (totalProcesados / capacidadMaxima) * 100;
    }
}