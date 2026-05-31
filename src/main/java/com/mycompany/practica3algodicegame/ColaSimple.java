package com.mycompany.practica3algodicegame;

// Clase de cola simple generica
public class ColaSimple<T> {

    // Arreglo donde se almacenan los datos
    private T[] cola;

    // Posicion del ultimo elemento
    private int fin;

    // Posicion del primer elemento
    private int inicio;

    // Tamaño maximo de la cola
    private int max;

    // Constructor de la cola
    public ColaSimple(int max) {
        this.max = max;
        this.inicio = -1;
        this.fin = -1;
        this.cola = (T[]) new Object[max];
    }

    // Inserta un elemento al final de la cola
    public void insertar(T dato) {
        if (fin == max - 1) {
            System.out.println("Desbordamiento");
        } else {
            if (fin == -1) {
                inicio = 0;
            }

            fin++;
            cola[fin] = dato;
        }
    }

    // Elimina el elemento del frente de la cola
    public T eliminar() {
        if (inicio == -1) {
            System.out.println("Subdesbordamiento");
            return null;
        }

        T dato = cola[inicio];

        if (inicio == fin) {
            inicio = -1;
            fin = -1;
        } else {
            inicio++;
        }

        return dato;
    }

    // Verifica si la cola esta vacia
    public boolean estaVacia() {
        return inicio == -1;
    }

    // Muestra los elementos de la cola
    public void mostrar() {
        if (inicio == -1) {
            System.out.println("Cola vacia");
            return;
        }

        System.out.print("Elementos en cola: ");

        for (int i = inicio; i <= fin; i++) {
            System.out.print(cola[i] + " ");
        }

        System.out.println();
    }
}