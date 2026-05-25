package com.mycompany.proyecto.pa;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class ProyectoPA{
     static Scanner teclado = new Scanner(System.in);
    static Random random = new Random();

    static ArrayList<Integer> historial = new ArrayList<>();
                                                                    //vaiables globales
    static String[] topJugadores = new String[2];
    static int[] topRondas = new int[2];

    static boolean juegoTerminado = false;
    static int rondas = 0;
    
    public static void menuPrincipal() {   /*Menu, 4 opciones con las que el usuario va a interactuar,
                                            las opciones hacen llamado a los siguientes metodos,
                                            por ultimo aqui se encuentra un TRY en caso de que el usuario
                                            escriba una letra en vez de un numero que es la respuesta recibida*/
        int opcion = 0;
        while (opcion != 4) {

            System.out.println("\n========== RULETA RUSA ==========");
            System.out.println("1. Jugar");
            System.out.println("2. Ver historial");
            System.out.println("3. Ver Top 2");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            try {

                opcion = teclado.nextInt();

                switch (opcion) {

                    case 1:

                        iniciarJuego();
                        break;

                    case 2:

                        mostrarHistorial();
                        break;

                    case 3:

                        mostrarTop();
                        break;

                    case 4:

                        System.out.println("\nGracias por jugar."); //no necesita de un metodo por separado 
                        break;                                     //en caso de que el usuario quiera salir del juego

                    default:

                        System.out.println("Opcion invalida.");

                }

            } catch (InputMismatchException e) {

                System.out.println("ERROR: Debe ingresar numeros.");
                teclado.nextLine();

            }

        }

    }


    public static void iniciarJuego() {       /*primera opcion, pide nombre del usuario (sirve para el top 2(tercera opcion)),
                                                se hace llamado a una funcion que es como tal la ruleta rusa tambien permite
                                                vover a jugar y repetir el proceso en caso de que sobreviva*/

        teclado.nextLine();

        System.out.print("\nIngrese su nombre: ");
        String nombre = teclado.nextLine();

        rondas = 0;
        juegoTerminado = false;

        System.out.println("\n¡Comienza el juego!");

        while (!juegoTerminado) {

            jugarRonda();

            if (!juegoTerminado) {

                System.out.print("\n¿Desea continuar? (si/no): ");
                String continuar = teclado.next();

                while (!continuar.equalsIgnoreCase("si")
                        && !continuar.equalsIgnoreCase("no")) {

                    System.out.print("Respuesta invalida. Escriba si o no: ");
                    continuar = teclado.next();

                }

                if (continuar.equalsIgnoreCase("no")) {

                    System.out.println("\nTe retiraste del juego.");
                    System.out.println("Rondas sobrevividas: " + rondas); //esto servira tambien para el historial y la tercera opcion

                    guardarRecord(nombre, rondas);

                    juegoTerminado = true;

                }

            } else {

                guardarRecord(nombre, rondas - 1);

            }

        }

    }

    public static void jugarRonda() {         //ruleta rusa.

        rondas++;
        int bala = random.nextInt(6) + 1;
        int numeroJugador = 0;
        boolean numeroValido = false;

        System.out.println("\n========== RONDA " + rondas + " ==========");
        while (!numeroValido) {

            try {

                System.out.print("Elija un numero del 1 al 6: ");       //en caso de elejir el numero perdedor
                numeroJugador = teclado.nextInt();                        //el juego termina 

                if (numeroJugador >= 1 && numeroJugador <= 6) {
                    numeroValido = true;

                } else {
                    System.out.println("El numero debe estar entre 1 y 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Solo puede ingresar numeros.");   //aqui tambien se implementa el TRY 
                teclado.nextLine();                                         //ya que se puede cometer el mismo error que con el menu
            }
        }
        historial.add(numeroJugador);

        System.out.println("Tu numero fue: " + numeroJugador);
        System.out.println("Numero secreto: " + bala);

        if (numeroJugador == bala) {
            System.out.println("\n ¡BOOM! Has perdido.");
            System.out.println("Sobreviviste " + (rondas - 1) + " rondas."); //muestra cuantas rondas de las que jugo sobrevivio
            juegoTerminado = true;

        } else {
            System.out.println(" Sobreviviste esta ronda.");
        }
    }
    public static void mostrarHistorial() {                 //segunda opcion, por si el usuario quiere conocer sus anteriores jugadas

        System.out.println("\n========== HISTORIAL ==========");

        if (historial.isEmpty()) {
            System.out.println("No hay historial disponible."); // en caso de que se eija la opcion y no se haya jugado

        } else {

            int i = 0;
            while (i < historial.size()) {

                System.out.println(
                        "Ronda "
                        + (i + 1)
                        + ": Numero elegido -> "
                        + historial.get(i)
                );
                i++;
            }
        }
    }
    public static void guardarRecord(String nombre, int puntaje) { //funcion para la segunda y tercera opcion, solo guarda el record

        if (puntaje > topRondas[0]) {

            topRondas[1] = topRondas[0];
            topJugadores[1] = topJugadores[0];
            topRondas[0] = puntaje;
            topJugadores[0] = nombre;

        } else if (puntaje > topRondas[1]) {

            topRondas[1] = puntaje;
            topJugadores[1] = nombre;

        }

    }
    public static void mostrarTop() {               //tercera opcion, por mas jugadores que hayan solo se mostraran los mejores dos

        System.out.println("\n========== TOP 2 ==========");
        int i = 0;

        while (i < 2) {

            if (topJugadores[i] != null) {
                System.out.println(
                        (i + 1)
                        + ". "
                        + topJugadores[i]
                        + " - "
                        + topRondas[i]
                        + " rondas"
                );
            } else {

                System.out.println((i + 1) + ". Sin registro");      //en caso de que no hayan jugadores porque no se ha jugado
            }
            i++;
        }
    }
    public static void main(String[] args) {    //MAIN, menuPrincipal es el primer metodo que se escribio
        menuPrincipal();
    }
}
