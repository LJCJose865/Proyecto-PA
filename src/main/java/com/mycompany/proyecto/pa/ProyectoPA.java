package com.mycompany.proyecto.pa;
//Importamos las librerias necesarias para el proyecto
import java.util.Scanner;
import java.util.Random;

/**
 *@author KatalinaFajardo
 * @author JosePerez
 * @author NicolasCorrea 
 * @author DanielOrtiz
 * @author AlejandroBonilla.
 */
public class ProyectoPA {
    //esta es para declarar una variable global que guarde la posicion de la bala en el tambor del artma (Revolver).
    static int posicionBala;

    public static void cargarRevolver(Random rand){
        posicionBala = rand.nextInt(6) + 1; //esta funcion es la que llama al alibreria Ramdom para generar un nuemro aleatiorio entre 1 y 6.
    }//el numero asignado por esta función representa endonde esta la bala en el revolver.

    //creacion de metodo para disparar el revolver, simula el disparo generando otra posición aleatoria.
    public static boolean disparar(Random rand){
        int disparo = rand .nextInt(6) + 1;
        return disparo == posicionBala; //significa que si la posicion del disparo nes igual a la de la bala, el jugador esta muerto (osea perdio), de lo contrario sigue jugando.
    }

    //para solicitar el numero de jugadores, nombres, en resumen tiene todo lo necesario para iniciar el juego.
    public static void jugar(Scanner sc, Random rand){
        System.out.println("Bienvenido a la Ruleta Rusa.");
        try{

            //para averiguar cuantos van a jugar, limite 6.
            System.out.print("Ingrese el numero de jugadores (2-6): ");
            int numJugadores = sc.nextInt();
            sc.nextLine(); //cierra el scanner.
            //Se crea un arreglo para guardar los nombres de los jugadores.
            String[] jugadores = new String[numJugadores];
            for (int i = 0; i < numJugadores; i++){
                System.out.print("Ingrese el Nombre del jugador " + (i + 1) + ": ");
                jugadores[i] = sc.nextLine();
            }
            cargarRevolver(rand); //carga el revolver antes de iniciar el juego.
            boolean juegoActivado = true;
            int turno = 0; //esta es para controlar el turno del jugador.
    
            System.out.println("\n------¡Que comiense el Juego!------");
            //bucle para controlar el juego, se repite hasta que un jugador pierda.
            while(juegoActivado){
                String jugadorActual = jugadores[turno];
                System.out.println("\nEs turno de " + jugadorActual);
                System.out.println("Presiona Enter para disparar...:v..");
                sc.nextLine(); //para que el jugador solo presione enter para disparar.
    
                if(disparar(rand)){
                    System.out.println("¡TAN!*******:O" + jugadorActual + " ha perdido.");
                    juegoActivado = false; //finaliza el juego si uno de los jugadores pierde.
                }else{
                    System.out.println("CLICK! Sobreviviste!");
                    turno = (turno + 1) % numJugadores; //para pasar al siguiente jugador.
                }
            }
            System.out.println("Gracias por jugar!");
        } catch(Exception e){
            System.out.println("Error entrada invalida. Porfavor intenta de neuvo :)");
            sc.nextLine(); //limpia el scanner en caso de error.
        }
    }
  //metodo para mostrar el menu del juego.
    public static void mostrarMenu(Scanner sc, Random rand){
        boolean salir = false; 
        while(!salir){
            try{
                System.out.println("\n------Menu Principal------");
                System.out.println("1. Jugar");
                System.out.println("2. Ver las Reglas del Juego");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opcion: ");
                int opcion = sc.nextInt();
                sc.nextLine();

                switch(opcion){
                    case 1:
                        jugar(sc, rand); //inicia el juego.
                        break;
                    case 2:
                        System.out.println("\n------Reglas del Juego------");
                        System.out.println("1. El juego se juega con un revolver de 6 balas, pero solo una bala esta cargada.");
                        System.out.println("2. Los jugadores se turnan para disparar el revolver apuntandose a si mismos.");
                        System.out.println("3. Si un jugador dispara y la bala esta en la posicion, ese jugador pierde y el juego termina.");
                        break;
                    case 3:
                        System.out.println("Gracias por jugar! Hasta luego.");
                        salir = true; //sale del menu y termina el programa.
                        break;    
                    default:
                        System.out.println("Opcion invalida.");
                }
            } catch(Exception e){
                System.out.println("Error entrada invalida. Porfavor intenta de nuevo :)");
                sc.nextLine(); //limpia el scanner en caso de error.
            }

        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        mostrarMenu(sc, rand); //muestra el menu principal al iniciar el programa.
        sc.close(); //cierra el scanner al finalizar el programa.
    }
}
