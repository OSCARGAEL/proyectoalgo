package com.mycompany.practica3algodicegame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// Interfaz grafica del juego Number Match
public class GUI extends Application {

    private Juego juego;

    private GridPane tableroVisual;
    private Label etiquetaInformacion;

    private TextField campoFilas;
    private TextField campoColumnas;

    private Node nodoSeleccionadoUno;

    private Node nodoPistaUno;
    private Node nodoPistaDos;

    // Inicia la aplicacion JavaFX
    @Override
    public void start(Stage escenarioPrincipal) {

        tableroVisual = new GridPane();
        tableroVisual.setHgap(5);
        tableroVisual.setVgap(5);
        tableroVisual.setAlignment(Pos.CENTER);

        etiquetaInformacion = new Label("Configura el tablero e inicia una partida.");

        campoFilas = new TextField("4");
        campoColumnas = new TextField("10");

        restringirCampoANumeros(campoFilas);
        restringirCampoANumeros(campoColumnas);

        Button botonIniciar = new Button("Iniciar");
        Button botonPista = new Button("Pista");
        Button botonDeshacer = new Button("Deshacer");

        botonIniciar.setOnAction(e -> iniciarPartidaConConfiguracion());

        botonPista.setOnAction(e -> mostrarPistaEnEtiqueta());

        botonDeshacer.setOnAction(e -> deshacerMovimiento());

        HBox panelSuperior = new HBox(10);

        panelSuperior.setAlignment(Pos.CENTER);

        panelSuperior.getChildren().addAll(
                new Label("Filas:"),
                campoFilas,
                new Label("Columnas:"),
                campoColumnas,
                botonIniciar,
                botonPista,
                botonDeshacer
        );

        BorderPane raiz = new BorderPane();

        raiz.setTop(panelSuperior);
        raiz.setCenter(tableroVisual);
        raiz.setBottom(etiquetaInformacion);

        BorderPane.setAlignment(etiquetaInformacion, Pos.CENTER);

        Scene escena = new Scene(raiz, 1080, 720);

        escenarioPrincipal.setTitle("Number Match");
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
    }

    // Restringe un TextField solo a numeros
    private void restringirCampoANumeros(TextField campoTexto) {

        campoTexto.textProperty().addListener((observable, valorAnterior, valorNuevo) -> {

            if (!valorNuevo.matches("\\d*")) {
                campoTexto.setText(valorNuevo.replaceAll("[^\\d]", ""));
            }
        });
    }

    // Inicia una partida usando la configuracion indicada
    private void iniciarPartidaConConfiguracion() {

        if (campoFilas.getText().isEmpty() ||
                campoColumnas.getText().isEmpty()) {

            etiquetaInformacion.setText("Ingresa filas y columnas.");
            return;
        }

        int filas = Integer.parseInt(campoFilas.getText());
        int columnas = Integer.parseInt(campoColumnas.getText());

        if (filas < 4 || columnas < 10) {
            etiquetaInformacion.setText("Minimo 4 filas y 10 columnas.");
            return;
        }

        if (filas > 15 || columnas > 20) {
            etiquetaInformacion.setText("Limite excedido.");
            return;
        }

        juego = new Juego(filas, columnas);
        juego.iniciarNuevaPartida();

        nodoSeleccionadoUno = null;
        nodoPistaUno = null;
        nodoPistaDos = null;

        actualizarVistaCompleta();

        etiquetaInformacion.setText("Partida iniciada.");
    }

    // Reinicia la partida actual
    private void reiniciarPartida() {

        if (juego == null) {
            return;
        }

        juego.iniciarNuevaPartida();

        nodoSeleccionadoUno = null;
        nodoPistaUno = null;
        nodoPistaDos = null;

        actualizarVistaCompleta();

        etiquetaInformacion.setText("Partida reiniciada.");
    }

    // Actualiza visualmente todo el tablero
    private void actualizarVistaCompleta() {

        tableroVisual.setDisable(false);
        tableroVisual.getChildren().clear();

        if (juego == null) {
            return;
        }

        Tablero tablero = juego.obtenerTableroJuego();

        for (Node nodoActual : tablero.obtenerNodosDelTablero()) {

            int fila =
                    tablero.obtenerFilaDeNodo(nodoActual);

            int columna =
                    tablero.obtenerColumnaDeNodo(nodoActual);

            Button botonNodo = new Button();

            botonNodo.setMinSize(50, 50);

            if (tablero.verificarSiNodoEstaEliminado(nodoActual)) {

                botonNodo.setText("");
                botonNodo.setDisable(true);

            } else {

                botonNodo.setText(
                        String.valueOf(nodoActual.getNumber()));

                botonNodo.setOnAction(
                        e -> manejarSeleccionDeNodo(nodoActual));
            }

            if (nodoActual == nodoSeleccionadoUno) {

                botonNodo.setStyle(
                        "-fx-border-color: blue; " +
                                "-fx-border-width: 3;"
                );
            }

            if (nodoActual == nodoPistaUno ||
                    nodoActual == nodoPistaDos) {

                botonNodo.setStyle(
                        "-fx-border-color: green; " +
                                "-fx-border-width: 3; " +
                                "-fx-text-fill: green;"
                );
            }

            tableroVisual.add(
                    botonNodo,
                    columna,
                    fila
            );
        }

        if (juego.verificarSiYaNoHayMovimientosDisponibles()) {

            etiquetaInformacion.setText(
                    "Juego terminado. Ya no hay movimientos."
            );

            desactivarTablero();
        }
    }

    // Maneja la seleccion de nodos
    private void manejarSeleccionDeNodo(Node nodoActual) {

        nodoPistaUno = null;
        nodoPistaDos = null;

        if (nodoSeleccionadoUno == null) {

            nodoSeleccionadoUno = nodoActual;

            actualizarVistaCompleta();

            return;
        }

        Node nodoSeleccionadoDos = nodoActual;

        boolean movimientoRealizado =
                juego.realizarMovimientoEntreNodos(
                        nodoSeleccionadoUno,
                        nodoSeleccionadoDos
                );

        if (movimientoRealizado) {

            etiquetaInformacion.setText(
                    "Movimiento realizado. Concordancias: "
                            + juego.obtenerTotalConcordanciasEncontradas()
                            + " | Pendientes: "
                            + juego.contarConcordanciasPendientesEnTablero()
            );

        } else {

            etiquetaInformacion.setText(
                    "Movimiento invalido."
            );
        }

        nodoSeleccionadoUno = null;

        actualizarVistaCompleta();
    }

    // Muestra una pista disponible
    private void mostrarPistaEnEtiqueta() {

        if (juego == null) {
            return;
        }

        Pista pistaDisponible =
                juego.buscarPistaDisponibleEnTablero();

        if (pistaDisponible == null) {

            etiquetaInformacion.setText(
                    "No hay pistas disponibles."
            );

            return;
        }

        nodoPistaUno =
                pistaDisponible.obtenerNodoUno();

        nodoPistaDos =
                pistaDisponible.obtenerNodoDos();

        actualizarVistaCompleta();

        etiquetaInformacion.setText(
                "Pista encontrada."
        );
    }

    // Deshace el ultimo movimiento
    private void deshacerMovimiento() {

        if (juego == null) {
            return;
        }

        boolean resultado =
                juego.deshacerUltimoMovimientoRealizado();

        if (resultado) {

            etiquetaInformacion.setText(
                    "Movimiento deshecho."
            );

        } else {

            etiquetaInformacion.setText(
                    "No hay movimientos para deshacer."
            );
        }

        nodoSeleccionadoUno = null;
        nodoPistaUno = null;
        nodoPistaDos = null;

        actualizarVistaCompleta();
    }

    // Desactiva el tablero visual
    private void desactivarTablero() {

        tableroVisual.setDisable(true);
    }

    // Metodo principal
    public static void main(String[] args) {
        launch(args);
    }
}