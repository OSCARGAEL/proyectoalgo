/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica3algodicegame;

/**
 *
 * @author HP
 */
import java.util.Random;

public class Dado {

    private Random generador;

    // Inicializa el generador de numeros aleatorios.
    public Dado() {
        generador = new Random();
    }

    // Genera un numero aleatorio entre 1 y 6.
    public int lanzarDado() {
        return generador.nextInt(6) + 1;
    }
}