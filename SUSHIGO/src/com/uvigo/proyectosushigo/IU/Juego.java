/**
 * Representa el juego del sushiGo, con sus reglas.
 * Se recomienda una implementación modular.
 */
package com.uvigo.proyectosushigo.IU;

import com.uvigo.poyectosushigo.CORE.*;

import static com.uvigo.proyectosushigo.IU.ES.*;
import java.util.LinkedList;
import java.util.List;


public class Juego {
    
    
    public static void inicio() {
        int numJugadores;
        do {

            numJugadores = pideNumero("Cuantos jugadores van a jugar(2-5): ");
        } while (numJugadores < 2 || numJugadores > 5);

        int numCartas = 0;  //numero de cartas con las que se va a jugar

        switch (numJugadores) {
            case 2:
                numCartas = 9;
                break;
            case 3:
                numCartas = 8;
                break;
            case 4:
                numCartas = 7;
                break;
            case 5:
                numCartas = 6;
                break;
        }

        List<Jugador> partida = new LinkedList<>();     //creamos una lista de jugadores

        
        
        for (int i = 1; i <= numJugadores; i++) {

            Jugador j = leeJugador(i);
            if (partida.isEmpty()) {
                partida.add(j);
            } else {

                for (Jugador x : partida) {
                    while (j.getNombre().equals(x.getNombre())) {
                        System.out.println("Ese jugador ya existe, introduce otro nombre: ");

                        j = leeJugador(i);

                    }
                }
                partida.add(j);
            }
        }

        for (int i = 0; i < 3; i++) {               //tres rondas

            System.out.println("\n");
            System.out.println("\n");
            System.out.println("\t\tRONDA " + (i + 1));
            System.out.println("\n");

            Baraja b = new Baraja();
            b.barajarCartas();                      //creamos la baraja y la barajamos

            // dar cartas
            for (Jugador j : partida) {
                j.CogerCartasBaraja(b, numCartas);      //damos las cartas a cada jugador
            }

            for (int j = 0; j < numCartas - 1; j++) {
                // se juegan la mano
                juego(partida);
                // se la cambian 
                cambiarMano(partida);

            }

            juego(partida);
            listar(partida);

            puntosMakis(partida, i);
            puntosTotales(partida, i);

            puntosRonda(partida, i);
            fin(partida);

        }

        // marcador
        System.out.println("\n\n");
        System.out.println("\t\tPUNTOS FINALES");
        System.out.println(marcador(partida) + "\n\t\t----------------------------------------");

        System.out.println("\n\n");
        ganador(partida);

        System.out.println("\n\n");
        System.out.println("\t\tFIN DEL JUEGO");

    }
    
    /**
     * Contructor del juego, recorre cada jugador de la partida y enseña sus cartas y la que escoge
     * 
     * @param partida 
     */
    public static void juego(List<Jugador> partida) {
        int numero;
        for (Jugador i : partida) {

            listar(partida);

            System.out.println("\tTURNO " + i.getNombre() + "\n");
            System.out.println("\tLas cartas de tu MANO son: ");
            System.out.println(i.getMano().toString());             
            do {
                numero = pideNumero(i.getNombre() + " escoge la CARTA que deseas jugar: ");
            } while (numero < 0 || numero >= i.getMano().NumeroCartasMano());
            i.EscogerCartaMesa(numero);                              

            System.out.println("\n\n");

        }
    }
    
    /**
     * 
     * Listar las cartas de la mesa en consola, recorriendo jugadores de la partida
     * 
     * @param partida 
     */
    public static void listar(List<Jugador> partida) {
        System.out.println("\tLas CARTAS de la MESA son: ");
        System.out.println("\n");
        for (Jugador j : partida) {

            System.out.println("\t\t" + j.getNombre());           
            System.out.println(j.getCartasMesa().toString());
            System.out.println("----------------------------------------");
        }
    }
    
    /**
     * Cambia la mano de los distintos jugadores (void), recorriendo jugadores de la partida
     * 
     * @param partida 
     * 
     */
    public static void cambiarMano(List<Jugador> partida) {
        Mano aux = partida.get((partida.size() - 1)).getMano();
        Mano aux2;
        for (Jugador j : partida) {                             
            aux2 = j.getMano();
            j.setMano(aux);
            aux = aux2;
        }
    }
    
    /**
     * Muestra un string con el marcador, recorriendo jugadores de la partida
     * 
     * @param partida
     * @return  marcador (string)
     */
    public static String marcador(List<Jugador> partida) {
        StringBuilder sb = new StringBuilder();
        for (Jugador j : partida) {
            sb.append("\n\t\t----------------------------------------\n").
                    append("\t\t|   ").append(j.getNombre()).append("  | ").append(j.getPuntosTotales()).append(" puntos").append("  |");     
        }
        return sb.toString();
    }
    
    /**
     * Calcula los puntos de un ronda,  y las enseña por consola
     * 
     * @param partida
     * @param i 
     */
    public static void puntosRonda(List<Jugador> partida, int i) {
        int y = 0;

        while (y < i + 1) {
            System.out.println("\n----------------------------------------");
            System.out.println("\tPUNTOS RONDA " + (y + 1));

            for (Jugador j : partida) {
                System.out.println("\tPuntos " + j.getNombre() + ": " + j.getPuntosRonda(y));

            }
            y++;
        }
    }
    
    /**
     *  Elimina las cartas de la mesa.
     * 
     * @param partida 
     */
    public static void fin(List<Jugador> partida) {
        for (Jugador j : partida) {
            j.getCartasMesa().eliminar();          
        }
    }
    
    /**
     * Calcula los puntos totales de la partida
     * 
     * @param partida
     * @param i
     */
    private static void puntosTotales(List<Jugador> partida, int i) {
        System.out.println("\tPUNTOS TOTALES");

        for (Jugador j : partida) {
            j.insertarPuntos(i, j.getCartasMesa().calcularPuntuacionMesa());
            System.out.println("\tPuntos " + j.getNombre() + " : " + j.getPuntosTotales());
        }
    }
    
    /**
     * muestra el ganador de la partida
     * 
     * @param partida 
     */
    private static void ganador(List<Jugador> partida) {
        Jugador jug = partida.get(0);

        for (Jugador j : partida) {
            if (j.getPuntosTotales() > jug.getPuntosTotales()) {
                jug = j;

            }                                           //muestra el ganador de la partida
        }
        for (Jugador j : partida) {
            if (jug.getPuntosTotales() == j.getPuntosTotales()) {
                System.out.println("\t\tÂ¡ EL GANADOR ES " + j.getNombre() + " !");

            }
        }

    }
    
    /**
     * 
     * Calcula el jugador que se lleva los puntos de makis
     * 
     * @param partida 
     * @param i 
     */
    private static void puntosMakis(List<Jugador> partida, int i) {

        Jugador jug = partida.get(0);

        for (Jugador j : partida) {
            if (j.getCartasMesa().calcularNumeroRollitos() > jug.getCartasMesa().calcularNumeroRollitos()) {  //compara los jugadores con el primero
                //en caso de que uno tenga mas rollitos que el primero
                jug = j;                                                                                      //se pasa a comparar el resto de jugadores con el

            }
        }
        int cont = 0;
        for (Jugador j : partida) {
            if (jug.getCartasMesa().calcularNumeroRollitos() == j.getCartasMesa().calcularNumeroRollitos()) { //si tienen el mismo numero de rollitos 
                cont++;                                                                                  //aumenta en uno el contador
            }
        }
        for (Jugador j : partida) {
            if (jug.getCartasMesa().calcularNumeroRollitos() == j.getCartasMesa().calcularNumeroRollitos()) {
                j.insertarPuntos(i, ((int) (6 / cont)));                                                   //se dividen los 6 puntos entre el numero
                //de jugadores empatados
            }
        }
    }
}
