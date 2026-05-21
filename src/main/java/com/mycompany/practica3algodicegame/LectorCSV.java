package com.mycompany.practica3algodicegame;

import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LectorCSV {

    // Lee el archivo CSV usando OpenCSV.
    public ArrayList<Videojuego> leerVideojuegos(String nombreArchivo) {

        ArrayList<Videojuego> videojuegos = new ArrayList<>();

        try {

            InputStream entrada = getClass().getResourceAsStream("/" + nombreArchivo);

            CSVReader lector = new CSVReader(new InputStreamReader(entrada));

            String[] linea;

            lector.readNext();

            while ((linea = lector.readNext()) != null) {

                int id = convertirEntero(linea[0]);
                String titulo = linea[1];
                String fechaLanzamiento = linea[2];
                String equipo = linea[3];
                double calificacion = convertirDouble(linea[4]);
                String vecesListado = linea[5];
                String numeroResenas = linea[6];
                String generos = linea[7];
                String resumen = linea[8];
                String resenas = linea[9];
                String plays = linea[10];
                String playing = linea[11];
                String backlogs = linea[12];
                String wishlist = linea[13];

                Videojuego videojuego = new Videojuego(
                        id,
                        titulo,
                        fechaLanzamiento,
                        equipo,
                        calificacion,
                        vecesListado,
                        numeroResenas,
                        generos,
                        resumen,
                        resenas,
                        plays,
                        playing,
                        backlogs,
                        wishlist
                );

                videojuegos.add(videojuego);
            }

            lector.close();

        } catch (Exception e) {
            System.out.println("Error leyendo CSV: " + e.getMessage());
        }

        return videojuegos;
    }

    // Convierte texto a entero.
    private int convertirEntero(String texto) {

        try {
            return Integer.parseInt(texto);
        } catch (Exception e) {
            return 0;
        }
    }

    // Convierte texto a decimal.
    private double convertirDouble(String texto) {

        try {
            return Double.parseDouble(texto);
        } catch (Exception e) {
            return 0;
        }
    }
}