package com.mycompany.practica3algodicegame;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class OrdenadorVideojuegos {

    // Ordena la lista usando seleccion directa.
    public void seleccionDirecta(ArrayList<Videojuego> lista, Comparator<Videojuego> comparador) {
        for (int i = 0; i < lista.size() - 1; i++) {
            int posicionMenor = i;

            for (int j = i + 1; j < lista.size(); j++) {
                if (comparador.compare(lista.get(j), lista.get(posicionMenor)) < 0) {
                    posicionMenor = j;
                }
            }

            intercambiar(lista, i, posicionMenor);
        }
    }

    // Ordena la lista usando shell sort.
    public void shellSort(ArrayList<Videojuego> lista, Comparator<Videojuego> comparador) {
        int salto = lista.size() / 2;

        while (salto > 0) {
            for (int i = salto; i < lista.size(); i++) {
                Videojuego temporal = lista.get(i);
                int j = i;

                while (j >= salto && comparador.compare(lista.get(j - salto), temporal) > 0) {
                    lista.set(j, lista.get(j - salto));
                    j = j - salto;
                }

                lista.set(j, temporal);
            }

            salto = salto / 2;
        }
    }

    // Ordena la lista usando merge sort.
    public void mergeSort(ArrayList<Videojuego> lista, Comparator<Videojuego> comparador) {
        mergeSort(lista, 0, lista.size() - 1, comparador);
    }

    // Divide la lista para aplicar merge sort.
    private void mergeSort(ArrayList<Videojuego> lista, int izquierda, int derecha,
                           Comparator<Videojuego> comparador) {

        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;

            mergeSort(lista, izquierda, medio, comparador);
            mergeSort(lista, medio + 1, derecha, comparador);
            mezclar(lista, izquierda, medio, derecha, comparador);
        }
    }

    // Mezcla las partes ordenadas de merge sort.
    private void mezclar(ArrayList<Videojuego> lista, int izquierda, int medio, int derecha,
                         Comparator<Videojuego> comparador) {

        ArrayList<Videojuego> izquierdaLista = new ArrayList<>();
        ArrayList<Videojuego> derechaLista = new ArrayList<>();

        for (int i = izquierda; i <= medio; i++) {
            izquierdaLista.add(lista.get(i));
        }

        for (int i = medio + 1; i <= derecha; i++) {
            derechaLista.add(lista.get(i));
        }

        int i = 0;
        int j = 0;
        int k = izquierda;

        while (i < izquierdaLista.size() && j < derechaLista.size()) {
            if (comparador.compare(izquierdaLista.get(i), derechaLista.get(j)) <= 0) {
                lista.set(k, izquierdaLista.get(i));
                i++;
            } else {
                lista.set(k, derechaLista.get(j));
                j++;
            }
            k++;
        }

        while (i < izquierdaLista.size()) {
            lista.set(k, izquierdaLista.get(i));
            i++;
            k++;
        }

        while (j < derechaLista.size()) {
            lista.set(k, derechaLista.get(j));
            j++;
            k++;
        }
    }

    // Ordena la lista usando quick sort.
    public void quickSort(ArrayList<Videojuego> lista, Comparator<Videojuego> comparador) {
        quickSort(lista, 0, lista.size() - 1, comparador);
    }

    // Divide la lista para aplicar quick sort.
    private void quickSort(ArrayList<Videojuego> lista, int inicio, int fin,
                           Comparator<Videojuego> comparador) {

        if (inicio < fin) {
            int posicionPivote = particionar(lista, inicio, fin, comparador);

            quickSort(lista, inicio, posicionPivote - 1, comparador);
            quickSort(lista, posicionPivote + 1, fin, comparador);
        }
    }

    // Coloca el pivote en su posicion correcta.
    private int particionar(ArrayList<Videojuego> lista, int inicio, int fin,
                            Comparator<Videojuego> comparador) {

        Videojuego pivote = lista.get(fin);
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (comparador.compare(lista.get(j), pivote) <= 0) {
                i++;
                intercambiar(lista, i, j);
            }
        }

        intercambiar(lista, i + 1, fin);
        return i + 1;
    }

    // Ordena la lista usando radix sort para columnas numericas.
    public void radixSort(ArrayList<Videojuego> lista, String columna) {
        int maximo = obtenerMaximo(lista, columna);

        for (int exponente = 1; maximo / exponente > 0; exponente = exponente * 10) {
            countingSort(lista, columna, exponente);
        }
    }

    // Ordena por digitos para apoyar radix sort.
    private void countingSort(ArrayList<Videojuego> lista, String columna, int exponente) {
        int n = lista.size();
        ArrayList<Videojuego> salida = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            salida.add(null);
        }

        int[] conteo = new int[10];

        for (int i = 0; i < n; i++) {
            int valor = obtenerValorNumerico(lista.get(i), columna);
            int digito = (valor / exponente) % 10;
            conteo[digito]++;
        }

        for (int i = 1; i < 10; i++) {
            conteo[i] = conteo[i] + conteo[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int valor = obtenerValorNumerico(lista.get(i), columna);
            int digito = (valor / exponente) % 10;
            salida.set(conteo[digito] - 1, lista.get(i));
            conteo[digito]--;
        }

        for (int i = 0; i < n; i++) {
            lista.set(i, salida.get(i));
        }
    }

    // Ordena usando Arrays.sort de Java.
    public void arraysSort(ArrayList<Videojuego> lista, Comparator<Videojuego> comparador) {
        Videojuego[] arreglo = lista.toArray(new Videojuego[0]);
        Arrays.sort(arreglo, comparador);

        for (int i = 0; i < arreglo.length; i++) {
            lista.set(i, arreglo[i]);
        }
    }

    // Ordena usando Arrays.parallelSort de Java.
    public void arraysParallelSort(ArrayList<Videojuego> lista, Comparator<Videojuego> comparador) {
        Videojuego[] arreglo = lista.toArray(new Videojuego[0]);
        Arrays.parallelSort(arreglo, comparador);

        for (int i = 0; i < arreglo.length; i++) {
            lista.set(i, arreglo[i]);
        }
    }

    // Obtiene el comparador de acuerdo con la columna seleccionada.
    public Comparator<Videojuego> obtenerComparador(String columna) {
        switch (columna) {
            case "Title":
                return Comparator.comparing(Videojuego::getTitulo, String.CASE_INSENSITIVE_ORDER);

            case "Release Date":
                return Comparator.comparing(v -> convertirFecha(v.getFechaLanzamiento()));

            case "Team":
                return Comparator.comparing(Videojuego::getEquipo, String.CASE_INSENSITIVE_ORDER);

            case "Rating":
                return Comparator.comparingDouble(Videojuego::getCalificacion);

            case "Times Listed":
                return Comparator.comparingInt(v -> convertirCantidad(v.getVecesListado()));

            case "Number of Reviews":
                return Comparator.comparingInt(v -> convertirCantidad(v.getNumeroResenas()));

            case "Genres":
                return Comparator.comparing(Videojuego::getGeneros, String.CASE_INSENSITIVE_ORDER);

            case "Summary":
                return Comparator.comparing(Videojuego::getResumen, String.CASE_INSENSITIVE_ORDER);

            case "Reviews":
                return Comparator.comparing(Videojuego::getResenas, String.CASE_INSENSITIVE_ORDER);

            case "Plays":
                return Comparator.comparingInt(v -> convertirCantidad(v.getPlays()));

            case "Playing":
                return Comparator.comparingInt(v -> convertirCantidad(v.getPlaying()));

            case "Backlogs":
                return Comparator.comparingInt(v -> convertirCantidad(v.getBacklogs()));

            case "Wishlist":
                return Comparator.comparingInt(v -> convertirCantidad(v.getWishlist()));

            default:
                return Comparator.comparingInt(Videojuego::getId);
        }
    }

    // Revisa si una columna puede ordenarse con radix sort.
    public boolean columnaEsNumerica(String columna) {
        return columna.equals("Rating")
                || columna.equals("Times Listed")
                || columna.equals("Number of Reviews")
                || columna.equals("Plays")
                || columna.equals("Playing")
                || columna.equals("Backlogs")
                || columna.equals("Wishlist");
    }

    // Obtiene el valor numerico de una columna.
    private int obtenerValorNumerico(Videojuego videojuego, String columna) {
        switch (columna) {
            case "Rating":
                return (int) (videojuego.getCalificacion() * 100);

            case "Times Listed":
                return convertirCantidad(videojuego.getVecesListado());

            case "Number of Reviews":
                return convertirCantidad(videojuego.getNumeroResenas());

            case "Plays":
                return convertirCantidad(videojuego.getPlays());

            case "Playing":
                return convertirCantidad(videojuego.getPlaying());

            case "Backlogs":
                return convertirCantidad(videojuego.getBacklogs());

            case "Wishlist":
                return convertirCantidad(videojuego.getWishlist());

            default:
                return 0;
        }
    }

    // Obtiene el valor maximo para radix sort.
    private int obtenerMaximo(ArrayList<Videojuego> lista, String columna) {
        int maximo = 0;

        for (Videojuego videojuego : lista) {
            int valor = obtenerValorNumerico(videojuego, columna);

            if (valor > maximo) {
                maximo = valor;
            }
        }

        return maximo;
    }

    // Convierte valores como 3.9K a numero entero.
    private int convertirCantidad(String texto) {
        try {
            texto = texto.replace("\"", "").trim();

            if (texto.endsWith("K")) {
                texto = texto.replace("K", "");
                return (int) (Double.parseDouble(texto) * 1000);
            }

            return (int) Double.parseDouble(texto);

        } catch (Exception e) {
            return 0;
        }
    }

    // Convierte texto de fecha a LocalDate.
    private LocalDate convertirFecha(String fechaTexto) {
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
            return LocalDate.parse(fechaTexto, formato);
        } catch (Exception e) {
            return LocalDate.MIN;
        }
    }

    // Intercambia dos posiciones de la lista.
    private void intercambiar(ArrayList<Videojuego> lista, int i, int j) {
        Videojuego temporal = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temporal);
    }
}