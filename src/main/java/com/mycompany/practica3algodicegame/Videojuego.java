package com.mycompany.practica3algodicegame;

public class Videojuego {

    private int id;
    private String titulo;
    private String fechaLanzamiento;
    private String equipo;
    private double calificacion;
    private String vecesListado;
    private String numeroResenas;
    private String generos;
    private String resumen;
    private String resenas;
    private String plays;
    private String playing;
    private String backlogs;
    private String wishlist;

    public Videojuego(int id, String titulo, String fechaLanzamiento, String equipo,
                      double calificacion, String vecesListado, String numeroResenas,
                      String generos, String resumen, String resenas, String plays,
                      String playing, String backlogs, String wishlist) {

        this.id = id;
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.equipo = equipo;
        this.calificacion = calificacion;
        this.vecesListado = vecesListado;
        this.numeroResenas = numeroResenas;
        this.generos = generos;
        this.resumen = resumen;
        this.resenas = resenas;
        this.plays = plays;
        this.playing = playing;
        this.backlogs = backlogs;
        this.wishlist = wishlist;
    }

    // Regresa el identificador del videojuego.
    public int getId() {
        return id;
    }

    // Regresa el titulo del videojuego.
    public String getTitulo() {
        return titulo;
    }

    // Regresa la fecha de lanzamiento.
    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    // Regresa el equipo o desarrollador.
    public String getEquipo() {
        return equipo;
    }

    // Regresa la calificacion del videojuego.
    public double getCalificacion() {
        return calificacion;
    }

    // Regresa las veces que fue listado.
    public String getVecesListado() {
        return vecesListado;
    }

    // Regresa el numero de resenas.
    public String getNumeroResenas() {
        return numeroResenas;
    }

    // Regresa los generos del videojuego.
    public String getGeneros() {
        return generos;
    }

    // Regresa el resumen del videojuego.
    public String getResumen() {
        return resumen;
    }

    // Regresa las resenas del videojuego.
    public String getResenas() {
        return resenas;
    }

    // Regresa la cantidad de plays.
    public String getPlays() {
        return plays;
    }

    // Regresa la cantidad de jugadores activos.
    public String getPlaying() {
        return playing;
    }

    // Regresa la cantidad de backlogs.
    public String getBacklogs() {
        return backlogs;
    }

    // Regresa la cantidad de wishlist.
    public String getWishlist() {
        return wishlist;
    }
}