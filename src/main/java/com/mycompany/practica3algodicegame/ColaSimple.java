/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica3algodicegame;

/**
 *
 * @author HP
 */
public class ColaSimple<T> {

    private T[] colaSimple;
    private int fin;
    private int inicio;
    private int max;

    // Inicializa la cola con el tamaño maximo indicado.
    public ColaSimple(int max) {
        this.max = max;
        this.inicio = -1;
        this.fin = -1;
        this.colaSimple = (T[]) new Object[max];
    }

    // Inserta un elemento al final de la cola.
    public void insertar(T dato) {
        if (fin == max - 1) {
            System.out.println("Desbordamiento");
        } else {
            if (fin == -1) {
                inicio = 0;
            }
            fin++;
            colaSimple[fin] = dato;
        }
    }

    // Elimina y devuelve el elemento al frente de la cola.
    public T eliminar() {
        if (inicio == -1) {
            System.out.println("Subdesbordamiento");
            return null;
        }

        T dato = colaSimple[inicio];

        if (inicio == fin) {
            inicio = -1;
            fin = -1;
        } else {
            inicio++;
        }

        return dato;
    }

    // Devuelve el elemento al frente sin eliminarlo.
    public T frente() {
        if (inicio == -1) {
            return null;
        }

        return colaSimple[inicio];
    }

    // Indica si la cola esta vacia.
    public boolean estaVacia() {
        return inicio == -1;
    }

    // Devuelve la cantidad de elementos en la cola.
    public int cantidadElementos() {
        if (inicio == -1) {
            return 0;
        }

        return fin - inicio + 1;
    }

    // Devuelve un elemento segun su posicion relativa desde el inicio.
    public T obtenerElemento(int posicion) {
        if (inicio == -1) {
            return null;
        }

        int indiceReal = inicio + posicion;

        if (indiceReal < inicio || indiceReal > fin) {
            return null;
        }

        return colaSimple[indiceReal];
    }

    // Muestra los elementos actuales de la cola en consola.
    public void mostrar() {
        if (inicio == -1) {
            System.out.println("Cola Vacia");
            return;
        }

        System.out.print("Elementos en ColaSimple: ");
        for (int i = inicio; i <= fin; i++) {
            System.out.print(colaSimple[i] + " ");
        }
        System.out.println();
    }
}