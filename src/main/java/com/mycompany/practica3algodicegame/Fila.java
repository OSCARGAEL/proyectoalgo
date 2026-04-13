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

public class Fila {

    private String nombreFila;
    private ColaSimple<Cliente> colaClientes;
    private int totalProcesados;
    private int valorDado;

    // Crea una fila con nombre y estructura de cola.
    public Fila(String nombreFila) {
        this.nombreFila = nombreFila;
        this.colaClientes = new ColaSimple<>(500);
        this.totalProcesados = 0;
        this.valorDado = 0;
    }

    // Agrega un cliente al final de la cola.
    public void agregarClienteACola(Cliente cliente) {
        colaClientes.insertar(cliente);
    }

    // Remueve un cliente del inicio de la cola.
    public Cliente removerClienteDeCola() {
        return colaClientes.eliminar();
    }

    // Verifica si la cola esta vacia.
    public boolean estaColaVacia() {
        return colaClientes.estaVacia();
    }

    // Devuelve la cantidad de clientes en la cola.
    public int obtenerCantidadClientesEnCola() {
        return colaClientes.cantidadElementos();
    }

    // Devuelve una lista de clientes visibles para la interfaz.
    public List<Cliente> obtenerClientesVisibles(int cantidad) {
        List<Cliente> clientesVisibles = new ArrayList<>();

        int limite = Math.min(cantidad, colaClientes.cantidadElementos());

        for (int i = 0; i < limite; i++) {
            Cliente cliente = colaClientes.obtenerElemento(i);

            if (cliente != null) {
                clientesVisibles.add(cliente);
            }
        }

        return clientesVisibles;
    }

    // Incrementa el total de clientes procesados.
    public void incrementarClientesProcesados(int cantidad) {
        totalProcesados += cantidad;
    }

    // Devuelve el total de clientes procesados por la fila.
    public int obtenerTotalProcesados() {
        return totalProcesados;
    }

    // Asigna el valor actual del dado a la fila.
    public void asignarValorDado(int valor) {
        valorDado = valor;
    }

    // Devuelve el valor actual del dado de la fila.
    public int obtenerValorDado() {
        return valorDado;
    }

    // Devuelve el nombre de la fila.
    public String obtenerNombreFila() {
        return nombreFila;
    }
}