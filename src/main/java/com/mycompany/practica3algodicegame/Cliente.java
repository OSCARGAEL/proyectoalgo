/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica3algodicegame;

/**
 *
 * @author HP
 */
public class Cliente {

    private int idCliente;
    private int turnoLlegada;
    private boolean esNuevo;

    // Crea un cliente con sus datos principales.
    public Cliente(int idCliente, int turnoLlegada, boolean esNuevo) {
        this.idCliente = idCliente;
        this.turnoLlegada = turnoLlegada;
        this.esNuevo = esNuevo;
    }

    // Devuelve el identificador del cliente.
    public int obtenerIdCliente() {
        return idCliente;
    }

    // Devuelve el turno en el que llego el cliente.
    public int obtenerTurnoLlegada() {
        return turnoLlegada;
    }

    // Indica si el cliente es nuevo (azul).
    public boolean esClienteNuevo() {
        return esNuevo;
    }
}