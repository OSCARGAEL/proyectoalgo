/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practica3algodicegame;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GUI extends Application {

    private Simulador simulador;

    private Label etiquetaTurno;
    private Label etiquetaClientesEnSistema;
    private Label etiquetaActividad;
    private Label etiquetaEstadoSimulacion;

    private Button botonIniciar;
    private Button botonAccionTurno;
    private Button botonReiniciar;

    private List<FlowPane> panelesCirculosPorFila;
    private List<Label> etiquetasCantidadPorFila;
    private List<Label> etiquetasDadoPorFila;

    private int numeroFilas;
    private boolean simulacionIniciada;

    // Inicializa los valores principales de la interfaz.
    public GUI() {
        simulador = new Simulador();
        panelesCirculosPorFila = new ArrayList<>();
        etiquetasCantidadPorFila = new ArrayList<>();
        etiquetasDadoPorFila = new ArrayList<>();
        numeroFilas = 10;
        simulacionIniciada = false;
    }

    // Inicia la aplicacion JavaFX y construye la ventana principal.
    @Override
    public void start(Stage ventana) {
        BorderPane panelPrincipal = construirInterfaz();

        Scene escena = new Scene(panelPrincipal, 1450, 760);
        ventana.setTitle("Simulador de Linea de Produccion");
        ventana.setScene(escena);
        ventana.show();

        mostrarFilasVacias();
    }

    // Construye toda la interfaz principal del programa.
    public BorderPane construirInterfaz() {
        BorderPane panelPrincipal = new BorderPane();

        Label titulo = new Label("Simulador de Linea de Produccion");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        HBox panelBotones = crearPanelBotones();
        VBox encabezado = new VBox(10, titulo, panelBotones);
        encabezado.setAlignment(Pos.CENTER);
        encabezado.setPadding(new Insets(15));

        GridPane panelFilas = crearPanelFilas();
        VBox panelMetricas = crearPanelMetricas();

        panelPrincipal.setTop(encabezado);
        panelPrincipal.setCenter(panelFilas);
        panelPrincipal.setRight(panelMetricas);

        return panelPrincipal;
    }

    // Crea el panel visual que contiene todas las filas.
    public GridPane crearPanelFilas() {
        GridPane panelFilas = new GridPane();
        panelFilas.setHgap(15);
        panelFilas.setVgap(15);
        panelFilas.setAlignment(Pos.TOP_CENTER);
        panelFilas.setPadding(new Insets(20));

        for (int i = 1; i <= numeroFilas; i++) {
            VBox panelUnaFila = crearPanelUnaFila("Fila " + i);
            int columna = (i - 1) % 5;
            int fila = (i - 1) / 5;
            panelFilas.add(panelUnaFila, columna, fila);
        }

        return panelFilas;
    }

    // Crea el panel visual de una sola fila.
    public VBox crearPanelUnaFila(String nombreFila) {
        Label etiquetaNombre = new Label(nombreFila);
        etiquetaNombre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label etiquetaDado = new Label("Dado: 0");
        Label etiquetaCantidad = new Label("Cantidad: 0");

        FlowPane panelCirculos = crearContenedorClientes();

        panelesCirculosPorFila.add(panelCirculos);
        etiquetasCantidadPorFila.add(etiquetaCantidad);
        etiquetasDadoPorFila.add(etiquetaDado);

        VBox panelUnaFila = new VBox(18, etiquetaNombre, etiquetaDado, panelCirculos, etiquetaCantidad);
        panelUnaFila.setAlignment(Pos.TOP_CENTER);
        panelUnaFila.setPadding(new Insets(16));
        panelUnaFila.setPrefWidth(220);
        panelUnaFila.setMinHeight(280);
        panelUnaFila.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #f4f4f4;");

        return panelUnaFila;
    }

    // Crea un contenedor flexible donde se dibujaran los clientes de cada fila.
    public FlowPane crearContenedorClientes() {
        FlowPane contenedor = new FlowPane();
        contenedor.setHgap(5);
        contenedor.setVgap(5);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPrefWrapLength(125);
        contenedor.setMinHeight(140);
        contenedor.setPrefHeight(140);
        return contenedor;
    }

    // Crea el panel donde se muestran las metricas principales.
    public VBox crearPanelMetricas() {
        Label tituloMetricas = new Label("Metricas");
        tituloMetricas.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        etiquetaTurno = new Label("Turno: 0 / 20");
        etiquetaClientesEnSistema = new Label("Clientes en sistema: 0");
        etiquetaActividad = new Label("Actividad: 0.00 %");
        etiquetaEstadoSimulacion = new Label("Estado: Esperando inicio");

        VBox panelMetricas = new VBox(
                15,
                tituloMetricas,
                etiquetaTurno,
                etiquetaClientesEnSistema,
                etiquetaActividad,
                etiquetaEstadoSimulacion
        );

        panelMetricas.setPadding(new Insets(20));
        panelMetricas.setPrefWidth(260);
        panelMetricas.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #eaeaea;");

        return panelMetricas;
    }

    // Crea el panel de botones para controlar la simulacion.
    public HBox crearPanelBotones() {
        botonIniciar = new Button("Iniciar");
        botonAccionTurno = new Button("Girar");
        botonReiniciar = new Button("Reiniciar");

        botonAccionTurno.setDisable(true);
        botonReiniciar.setDisable(true);

        botonIniciar.setOnAction(e -> iniciarSimulacion());
        botonAccionTurno.setOnAction(e -> ejecutarAccionTurno());
        botonReiniciar.setOnAction(e -> reiniciarSimulacion());

        HBox panelBotones = new HBox(10, botonIniciar, botonAccionTurno, botonReiniciar);
        panelBotones.setAlignment(Pos.CENTER);

        return panelBotones;
    }

    // Inicializa la simulacion y actualiza la interfaz.
    public void iniciarSimulacion() {
        simulador.inicializarSimulacion(numeroFilas);
        simulacionIniciada = true;
        actualizarInterfaz();

        botonIniciar.setDisable(true);
        botonAccionTurno.setDisable(false);
        botonAccionTurno.setText("Girar");
        botonReiniciar.setDisable(false);
        etiquetaEstadoSimulacion.setText("Estado: Simulacion en ejecucion");
    }

    // Ejecuta la fase actual del turno, ya sea girar o mover.
    public void ejecutarAccionTurno() {
        if (!simulador.estanDadosGirados()) {
            simulador.girarDados();
            botonAccionTurno.setText("Mover");
            etiquetaEstadoSimulacion.setText("Estado: Dados girados");
        } else {
            simulador.moverSegunDados();
            botonAccionTurno.setText("Girar");
            etiquetaEstadoSimulacion.setText("Estado: Clientes movidos");
        }

        actualizarInterfaz();

        if (simulador.simulacionTerminada()) {
            botonAccionTurno.setDisable(true);
            etiquetaEstadoSimulacion.setText("Estado: Simulacion finalizada");
        }
    }

    // Reinicia la simulacion conservando la configuracion actual.
    public void reiniciarSimulacion() {
        simulador.reiniciarSimulacion();
        simulacionIniciada = true;
        actualizarInterfaz();

        botonAccionTurno.setDisable(false);
        botonAccionTurno.setText("Girar");
        etiquetaEstadoSimulacion.setText("Estado: Simulacion reiniciada");
    }

    // Muestra todas las filas visualmente vacias antes de iniciar la simulacion.
    public void mostrarFilasVacias() {
        for (int i = 0; i < panelesCirculosPorFila.size(); i++) {
            dibujarFilaVacia(panelesCirculosPorFila.get(i));
            etiquetasCantidadPorFila.get(i).setText("Cantidad: 0");
            etiquetasDadoPorFila.get(i).setText("Dado: 0");
        }
    }

    // Dibuja una fila vacia sin clientes visibles.
    public void dibujarFilaVacia(FlowPane panelCirculos) {
        panelCirculos.getChildren().clear();
    }

    // Actualiza todas las partes visuales de la interfaz.
    public void actualizarInterfaz() {
        if (!simulacionIniciada) {
            mostrarFilasVacias();
            actualizarMetricas();
            return;
        }

        actualizarVisualFilas();
        actualizarMetricas();
    }

    // Actualiza los clientes visibles, las cantidades y los dados de cada fila.
    public void actualizarVisualFilas() {
        List<Fila> filas = simulador.obtenerListaFilas();

        for (int i = 0; i < panelesCirculosPorFila.size(); i++) {
            if (i < filas.size()) {
                Fila filaActual = filas.get(i);
                List<Cliente> clientesVisibles = filaActual.obtenerClientesVisibles(filaActual.obtenerCantidadClientesEnCola());

                dibujarClientesFila(panelesCirculosPorFila.get(i), clientesVisibles);
                etiquetasCantidadPorFila.get(i).setText("Cantidad: " + filaActual.obtenerCantidadClientesEnCola());
                etiquetasDadoPorFila.get(i).setText("Dado: " + filaActual.obtenerValorDado());
            } else {
                dibujarFilaVacia(panelesCirculosPorFila.get(i));
                etiquetasCantidadPorFila.get(i).setText("Cantidad: 0");
                etiquetasDadoPorFila.get(i).setText("Dado: 0");
            }
        }
    }

    // Dibuja unicamente los clientes reales sin circulos vacios.
    public void dibujarClientesFila(FlowPane panelCirculos, List<Cliente> clientesVisibles) {
        panelCirculos.getChildren().clear();

        for (Cliente cliente : clientesVisibles) {
            Circle circulo = crearCirculoCliente(cliente);
            panelCirculos.getChildren().add(circulo);
        }
    }

    // Crea un circulo visual que representa un cliente.
    public Circle crearCirculoCliente(Cliente cliente) {
        Circle circulo = new Circle(7);

        if (cliente.esClienteNuevo()) {
            circulo.setFill(Color.DODGERBLUE);
            circulo.setStroke(Color.DARKBLUE);
        } else {
            circulo.setFill(Color.GRAY);
            circulo.setStroke(Color.DIMGRAY);
        }

        circulo.setStrokeWidth(0.5);

        return circulo;
    }

    // Actualiza las metricas generales del sistema.
    public void actualizarMetricas() {
        if (!simulacionIniciada) {
            etiquetaTurno.setText("Turno: 0 / " + simulador.obtenerTurnoMaximo());
            etiquetaClientesEnSistema.setText("Clientes en sistema: 0");
            etiquetaActividad.setText("Actividad: 0.00 %");
            return;
        }

        etiquetaTurno.setText("Turno: " + simulador.obtenerTurnoActual() + " / " + simulador.obtenerTurnoMaximo());
        etiquetaClientesEnSistema.setText("Clientes en sistema: " + simulador.obtenerTotalClientesEnSistema());
        etiquetaActividad.setText(String.format("Actividad: %.2f %%", simulador.calcularActividadSistema()));
    }

    // Lanza la aplicacion JavaFX.
    public static void main(String[] args) {
        launch(args);
    }
}