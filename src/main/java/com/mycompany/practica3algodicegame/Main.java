package com.mycompany.practica3algodicegame;
import java.util.Scanner;

// Clase principal del programa
public class Main {

    // Scanner para capturar datos del usuario
    private Scanner entrada;

    // Objeto del grafo
    private GrafoDirigidoAciclico grafo;

    // Constructor principal
    public Main() {
        entrada = new Scanner(System.in);
        grafo = crearGrafo();
    }

    // Metodo que inicia el programa y controla el menu
    public void iniciar() {
        int opcion = 0;

        while (opcion != 12) {
            mostrarMenu();

            try {
                opcion = entrada.nextInt();

                if (opcion == 1) {
                    insertarAristaMenu();
                } else if (opcion == 2) {
                    gradoEntradaMenu();
                } else if (opcion == 3) {
                    gradoSalidaMenu();
                } else if (opcion == 4) {
                    System.out.println("Cantidad de aristas: " + grafo.cuantasAristasHay());
                } else if (opcion == 5) {
                    adyacenteMenu();
                } else if (opcion == 6) {
                    conectadosMenu();
                } else if (opcion == 7) {
                    System.out.println("Topological Sort:");
                    System.out.println(grafo.topologicalSort());
                } else if (opcion == 8) {
                    System.out.println(grafo.mostrarEstructura());
                } else if (opcion == 9) {
                    System.out.println("Tiene ciclos: " + grafo.tieneCiclos());
                } else if (opcion == 10) {
                    grafo.eliminarAristas();
                    System.out.println("Todas las aristas fueron eliminadas");
                } else if (opcion == 11) {
                    grafo = crearGrafo();
                    System.out.println("Nuevo grafo creado");
                } else if (opcion == 12) {
                    System.out.println("Programa finalizado");
                } else {
                    System.out.println("Opcion invalida");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        entrada.close();
    }

    // Crea un nuevo grafo
    public GrafoDirigidoAciclico crearGrafo() {
        System.out.print("Cantidad de vertices: ");

        int n = entrada.nextInt();

        return new GrafoDirigidoAciclico(n);
    }

    // Muestra el menu principal
    public void mostrarMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Insertar arista");
        System.out.println("2. Grado de entrada");
        System.out.println("3. Grado de salida");
        System.out.println("4. Cantidad de aristas");
        System.out.println("5. Verificar adyacencia");
        System.out.println("6. Verificar conexion");
        System.out.println("7. Topological Sort");
        System.out.println("8. Mostrar estructura");
        System.out.println("9. Verificar ciclos");
        System.out.println("10. Eliminar aristas");
        System.out.println("11. Nuevo grafo");
        System.out.println("12. Salir");
        System.out.print("Opcion: ");
    }

    // Inserta una arista desde el menu
    public void insertarAristaMenu() {
        System.out.print("Vertice origen: ");
        int i = entrada.nextInt();

        System.out.print("Vertice destino: ");
        int j = entrada.nextInt();

        if (grafo.insertarArista(i, j)) {
            System.out.println("Arista insertada correctamente");
        } else {
            System.out.println("No se pudo insertar la arista");
        }
    }

    // Muestra el grado de entrada de un vertice
    public void gradoEntradaMenu() {
        System.out.print("Vertice: ");
        int i = entrada.nextInt();

        System.out.println("Grado de entrada: " + grafo.gradoDeEntrada(i));
    }

    // Muestra el grado de salida de un vertice
    public void gradoSalidaMenu() {
        System.out.print("Vertice: ");
        int i = entrada.nextInt();

        System.out.println("Grado de salida: " + grafo.gradoDeSalida(i));
    }

    // Verifica si dos vertices son adyacentes
    public void adyacenteMenu() {
        System.out.print("Vertice origen: ");
        int i = entrada.nextInt();

        System.out.print("Vertice destino: ");
        int j = entrada.nextInt();

        System.out.println("Adyacente: " + grafo.adyacente(i, j));
    }

    // Verifica si existe un camino entre dos vertices
    public void conectadosMenu() {
        System.out.print("Vertice origen: ");
        int i = entrada.nextInt();

        System.out.print("Vertice destino: ");
        int j = entrada.nextInt();

        System.out.println("Conectados: " + grafo.conectados(i, j));
    }

    // Metodo principal del programa
    public static void main(String[] args) {
        Main main = new Main();
        main.iniciar();
    }
}