package com.mycompany.practica3algodicegame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Cantidad de vertices: ");
        int n = entrada.nextInt();

        GrafoDirigidoAciclico grafo = new GrafoDirigidoAciclico(n);

        int opcion = 0;

        while (opcion != 9) {
            System.out.println("\n MENU ");
            System.out.println("1.- Insertar arista");
            System.out.println("2.- Grado de entrada");
            System.out.println("3.- Grado de salida");
            System.out.println("4.- Cantidad de aristas");
            System.out.println("5.- Verificar adyacencia");
            System.out.println("6.- Verificar conexion");
            System.out.println("7.- Topological Sort");
            System.out.println("8.- Mostrar estructura");
            System.out.println("9.- Salir");
            System.out.print("Opcion: ");

            opcion = entrada.nextInt();

            if (opcion == 1) {
                System.out.print("Vertice origen: ");
                int i = entrada.nextInt();

                System.out.print("Vertice destino: ");
                int j = entrada.nextInt();

                boolean insertada = grafo.insertarArista(i, j);

                if (insertada) {
                    System.out.println("Arista insertada correctamente");
                } else {
                    System.out.println("No se pudo insertar la arista");
                }
            } else if (opcion == 2) {
                System.out.print("Vertice: ");
                int i = entrada.nextInt();

                System.out.println("Grado de entrada: " + grafo.gradoDeEntrada(i));
            } else if (opcion == 3) {
                System.out.print("Vertice: ");
                int i = entrada.nextInt();

                System.out.println("Grado de salida: " + grafo.gradoDeSalida(i));
            } else if (opcion == 4) {
                System.out.println("Cantidad de aristas: " + grafo.cuantasAristasHay());
            } else if (opcion == 5) {
                System.out.print("Vertice origen: ");
                int i = entrada.nextInt();

                System.out.print("Vertice destino: ");
                int j = entrada.nextInt();

                System.out.println("Adyacente: " + grafo.adyacente(i, j));
            } else if (opcion == 6) {
                System.out.print("Vertice origen: ");
                int i = entrada.nextInt();

                System.out.print("Vertice destino: ");
                int j = entrada.nextInt();

                System.out.println("Conectados: " + grafo.conectados(i, j));
            } else if (opcion == 7) {
                System.out.println("Topological Sort:");
                System.out.println(grafo.topologicalSort());
            } else if (opcion == 8) {
                System.out.println(grafo.mostrarEstructura());
            } else if (opcion == 9) {
                System.out.println("Programa finalizado");
            } else {
                System.out.println("Opcion invalida");
            }
        }

        entrada.close();
    }
}