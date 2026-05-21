package com.mycompany.practica3algodicegame;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    private SimuladorOrdenamientos simulador;

    private TableView<Videojuego> tablaDatos;
    private TableView<Videojuego> tablaOrdenamiento;
    private TableView<ResultadoOrdenamiento> tablaResultados;

    private BarChart<String, Number> graficaTiempos;

    private ComboBox<String> comboMetodos;
    private ComboBox<String> comboColumnasOrdenamiento;
    private ComboBox<String> comboColumnasComparacion;

    private CheckBox checkSeleccionDirecta;
    private CheckBox checkShellSort;
    private CheckBox checkMergeSort;
    private CheckBox checkQuickSort;
    private CheckBox checkRadixSort;
    private CheckBox checkArraysSort;
    private CheckBox checkArraysParallelSort;

    private Label etiquetaEstado;

    private ArrayList<ResultadoOrdenamiento> resultadosActuales;

    @Override
    public void start(Stage stage) {
        simulador = new SimuladorOrdenamientos();
        simulador.cargarVideojuegos();

        resultadosActuales = new ArrayList<>();

        TabPane panelPestanas = new TabPane();

        Tab pestanaDatos = new Tab("Datos", crearVistaDatos());
        Tab pestanaOrdenamiento = new Tab("Ordenamiento", crearVistaOrdenamiento());
        Tab pestanaComparacion = new Tab("Comparacion", crearVistaComparacion());

        pestanaDatos.setClosable(false);
        pestanaOrdenamiento.setClosable(false);
        pestanaComparacion.setClosable(false);

        panelPestanas.getTabs().addAll(pestanaDatos, pestanaOrdenamiento, pestanaComparacion);

        Scene escena = new Scene(panelPestanas, 1300, 700);

        stage.setTitle("Practica 6 - Ordenamientos de Videojuegos");
        stage.setScene(escena);
        stage.show();
    }

    // Crea la vista principal de datos.
    private BorderPane crearVistaDatos() {
        BorderPane panel = new BorderPane();

        tablaDatos = new TableView<>();
        inicializarTablaVideojuegos(tablaDatos);
        actualizarTablaVideojuegos(tablaDatos, simulador.getVideojuegos());

        Label titulo = new Label("Dataset de videojuegos");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        VBox cajaSuperior = new VBox(10, titulo);
        cajaSuperior.setPadding(new Insets(15));

        panel.setTop(cajaSuperior);
        panel.setCenter(tablaDatos);

        return panel;
    }

    // Crea la vista para ordenar datos.
    private BorderPane crearVistaOrdenamiento() {
        BorderPane panel = new BorderPane();

        tablaOrdenamiento = new TableView<>();
        inicializarTablaVideojuegos(tablaOrdenamiento);
        actualizarTablaVideojuegos(tablaOrdenamiento, simulador.getVideojuegos());

        comboMetodos = new ComboBox<>();
        comboMetodos.getItems().addAll(
                "Seleccion Directa",
                "Shell Sort",
                "Merge Sort",
                "Quick Sort",
                "Radix Sort",
                "Arrays.sort",
                "Arrays.parallelSort"
        );
        comboMetodos.setValue("Quick Sort");

        comboColumnasOrdenamiento = new ComboBox<>();
        agregarColumnasCombo(comboColumnasOrdenamiento);
        comboColumnasOrdenamiento.setValue("Title");

        Button botonOrdenar = new Button("Ordenar");
        botonOrdenar.setOnAction(e -> ordenarDatos());

        etiquetaEstado = new Label("Selecciona un metodo y una columna.");

        HBox controles = new HBox(10,
                new Label("Metodo:"), comboMetodos,
                new Label("Columna:"), comboColumnasOrdenamiento,
                botonOrdenar,
                etiquetaEstado
        );
        controles.setPadding(new Insets(15));

        panel.setTop(controles);
        panel.setCenter(tablaOrdenamiento);

        return panel;
    }

    // Crea la vista de comparacion de tiempos.
    private BorderPane crearVistaComparacion() {
        BorderPane panel = new BorderPane();

        tablaResultados = new TableView<>();
        inicializarTablaResultados();

        inicializarGrafica();

        comboColumnasComparacion = new ComboBox<>();
        agregarColumnasCombo(comboColumnasComparacion);
        comboColumnasComparacion.setValue("Times Listed");

        Button botonComparar = new Button("Comparar todos");
        botonComparar.setOnAction(e -> compararTodos());

        inicializarChecksMetodos();

        HBox controlesPrincipales = new HBox(10,
                new Label("Columna:"), comboColumnasComparacion,
                botonComparar
        );
        controlesPrincipales.setPadding(new Insets(15));

        HBox controlesMetodos = new HBox(10,
                checkSeleccionDirecta,
                checkShellSort,
                checkMergeSort,
                checkQuickSort,
                checkRadixSort,
                checkArraysSort,
                checkArraysParallelSort
        );
        controlesMetodos.setPadding(new Insets(0, 15, 15, 15));

        VBox controles = new VBox(5, controlesPrincipales, controlesMetodos);

        VBox contenido = new VBox(15,
                tablaResultados,
                graficaTiempos
        );
        contenido.setPadding(new Insets(15));

        ScrollPane scroll = new ScrollPane(contenido);
        scroll.setFitToWidth(true);

        panel.setTop(controles);
        panel.setCenter(scroll);

        return panel;
    }

    // Inicializa los checkbox de cada metodo.
    private void inicializarChecksMetodos() {
        checkSeleccionDirecta = new CheckBox("Seleccion Directa");
        checkShellSort = new CheckBox("Shell Sort");
        checkMergeSort = new CheckBox("Merge Sort");
        checkQuickSort = new CheckBox("Quick Sort");
        checkRadixSort = new CheckBox("Radix Sort");
        checkArraysSort = new CheckBox("Arrays.sort");
        checkArraysParallelSort = new CheckBox("Arrays.parallelSort");

        checkSeleccionDirecta.setSelected(true);
        checkShellSort.setSelected(true);
        checkMergeSort.setSelected(true);
        checkQuickSort.setSelected(true);
        checkRadixSort.setSelected(true);
        checkArraysSort.setSelected(true);
        checkArraysParallelSort.setSelected(true);

        checkSeleccionDirecta.setOnAction(e -> actualizarGraficaConFiltros());
        checkShellSort.setOnAction(e -> actualizarGraficaConFiltros());
        checkMergeSort.setOnAction(e -> actualizarGraficaConFiltros());
        checkQuickSort.setOnAction(e -> actualizarGraficaConFiltros());
        checkRadixSort.setOnAction(e -> actualizarGraficaConFiltros());
        checkArraysSort.setOnAction(e -> actualizarGraficaConFiltros());
        checkArraysParallelSort.setOnAction(e -> actualizarGraficaConFiltros());
    }

    // Agrega las columnas disponibles al ComboBox.
    private void agregarColumnasCombo(ComboBox<String> combo) {
        combo.getItems().addAll(
                "Title",
                "Release Date",
                "Team",
                "Rating",
                "Times Listed",
                "Number of Reviews",
                "Genres",
                "Summary",
                "Reviews",
                "Plays",
                "Playing",
                "Backlogs",
                "Wishlist"
        );
    }

    // Inicializa las columnas de la tabla de videojuegos.
    private void inicializarTablaVideojuegos(TableView<Videojuego> tabla) {
        TableColumn<Videojuego, Integer> columnaId = new TableColumn<>("ID");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Videojuego, String> columnaTitulo = new TableColumn<>("Title");
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        TableColumn<Videojuego, String> columnaFecha = new TableColumn<>("Release Date");
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaLanzamiento"));

        TableColumn<Videojuego, String> columnaEquipo = new TableColumn<>("Team");
        columnaEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));

        TableColumn<Videojuego, Double> columnaCalificacion = new TableColumn<>("Rating");
        columnaCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));

        TableColumn<Videojuego, String> columnaVecesListado = new TableColumn<>("Times Listed");
        columnaVecesListado.setCellValueFactory(new PropertyValueFactory<>("vecesListado"));

        TableColumn<Videojuego, String> columnaNumeroResenas = new TableColumn<>("Number of Reviews");
        columnaNumeroResenas.setCellValueFactory(new PropertyValueFactory<>("numeroResenas"));

        TableColumn<Videojuego, String> columnaGeneros = new TableColumn<>("Genres");
        columnaGeneros.setCellValueFactory(new PropertyValueFactory<>("generos"));

        TableColumn<Videojuego, String> columnaResumen = new TableColumn<>("Summary");
        columnaResumen.setCellValueFactory(new PropertyValueFactory<>("resumen"));

        TableColumn<Videojuego, String> columnaResenas = new TableColumn<>("Reviews");
        columnaResenas.setCellValueFactory(new PropertyValueFactory<>("resenas"));

        TableColumn<Videojuego, String> columnaPlays = new TableColumn<>("Plays");
        columnaPlays.setCellValueFactory(new PropertyValueFactory<>("plays"));

        TableColumn<Videojuego, String> columnaPlaying = new TableColumn<>("Playing");
        columnaPlaying.setCellValueFactory(new PropertyValueFactory<>("playing"));

        TableColumn<Videojuego, String> columnaBacklogs = new TableColumn<>("Backlogs");
        columnaBacklogs.setCellValueFactory(new PropertyValueFactory<>("backlogs"));

        TableColumn<Videojuego, String> columnaWishlist = new TableColumn<>("Wishlist");
        columnaWishlist.setCellValueFactory(new PropertyValueFactory<>("wishlist"));

        tabla.getColumns().addAll(
                columnaId,
                columnaTitulo,
                columnaFecha,
                columnaEquipo,
                columnaCalificacion,
                columnaVecesListado,
                columnaNumeroResenas,
                columnaGeneros,
                columnaResumen,
                columnaResenas,
                columnaPlays,
                columnaPlaying,
                columnaBacklogs,
                columnaWishlist
        );
    }

    // Inicializa las columnas de la tabla de resultados.
    private void inicializarTablaResultados() {
        TableColumn<ResultadoOrdenamiento, String> columnaMetodo = new TableColumn<>("Metodo");
        columnaMetodo.setCellValueFactory(new PropertyValueFactory<>("metodo"));

        TableColumn<ResultadoOrdenamiento, String> columnaOrdenada = new TableColumn<>("Columna");
        columnaOrdenada.setCellValueFactory(new PropertyValueFactory<>("columna"));

        TableColumn<ResultadoOrdenamiento, Double> columnaTiempo = new TableColumn<>("Tiempo ms");
        columnaTiempo.setCellValueFactory(new PropertyValueFactory<>("tiempoMilisegundos"));

        tablaResultados.getColumns().addAll(columnaMetodo, columnaOrdenada, columnaTiempo);
    }

    // Inicializa la grafica de tiempos.
    private void inicializarGrafica() {
        CategoryAxis ejeX = new CategoryAxis();
        NumberAxis ejeY = new NumberAxis();

        ejeX.setLabel("Metodo de ordenamiento con tiempo");
        ejeY.setLabel("Tiempo en milisegundos");

        graficaTiempos = new BarChart<>(ejeX, ejeY);
        graficaTiempos.setTitle("Comparacion de tiempos");
        graficaTiempos.setMinHeight(400);
    }

    // Ordena los datos y actualiza la tabla de ordenamiento.
    private void ordenarDatos() {
        String metodo = comboMetodos.getValue();
        String columna = comboColumnasOrdenamiento.getValue();

        ResultadoOrdenamiento resultado = simulador.medirOrdenamiento(metodo, columna);
        ArrayList<Videojuego> listaOrdenada = simulador.ordenarVideojuegos(metodo, columna);

        actualizarTablaVideojuegos(tablaOrdenamiento, listaOrdenada);

        etiquetaEstado.setText("Tiempo: " + resultado.getTiempoMilisegundos() + " ms");
    }

    // Compara todos los metodos y actualiza tabla y grafica.
    private void compararTodos() {
        String columna = comboColumnasComparacion.getValue();

        resultadosActuales = simulador.compararTodos(columna);

        tablaResultados.setItems(FXCollections.observableArrayList(resultadosActuales));

        actualizarGraficaConFiltros();
    }

    // Actualiza una tabla de videojuegos con una lista.
    private void actualizarTablaVideojuegos(TableView<Videojuego> tabla, ArrayList<Videojuego> videojuegos) {
        tabla.setItems(FXCollections.observableArrayList(videojuegos));
    }

    // Actualiza la grafica mostrando solo los metodos seleccionados.
    private void actualizarGraficaConFiltros() {
        graficaTiempos.getData().clear();

        if (resultadosActuales == null || resultadosActuales.isEmpty()) {
            return;
        }

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Tiempo");

        for (ResultadoOrdenamiento resultado : resultadosActuales) {
            if (verificarSiMetodoEstaSeleccionado(resultado.getMetodo())) {
                serie.getData().add(new XYChart.Data<>(
                        obtenerEtiquetaMetodoConTiempo(resultado),
                        resultado.getTiempoMilisegundos()
                ));
            }
        }

        graficaTiempos.getData().add(serie);
    }

    // Verifica si el metodo esta marcado en los checkbox.
    private boolean verificarSiMetodoEstaSeleccionado(String metodo) {
        switch (metodo) {
            case "Seleccion Directa":
                return checkSeleccionDirecta.isSelected();

            case "Shell Sort":
                return checkShellSort.isSelected();

            case "Merge Sort":
                return checkMergeSort.isSelected();

            case "Quick Sort":
                return checkQuickSort.isSelected();

            case "Radix Sort":
                return checkRadixSort.isSelected();

            case "Arrays.sort":
                return checkArraysSort.isSelected();

            case "Arrays.parallelSort":
                return checkArraysParallelSort.isSelected();

            default:
                return false;
        }
    }

    // Crea una etiqueta con el metodo y su tiempo.
    private String obtenerEtiquetaMetodoConTiempo(ResultadoOrdenamiento resultado) {
        return resultado.getMetodo() + "\n"
                + String.format("%.4f", resultado.getTiempoMilisegundos()) + " ms";
    }

    // Inicia la aplicacion.
    public static void main(String[] args) {
        launch(args);
    }
}