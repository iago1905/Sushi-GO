/*
 * Representa a un jugador de la partida, identificado por el nombre 
 * Funcionalidad: escoge una carta de su mano; la colocará en su mesa; coge cartas de la baraja para la mano,
 *                entrega las cartas de su mano; coge las cartas de otra mano; calcula su puntuación por ronda;
 *                calcula su puntuación total; cuenta cuantos rollitos tiene en su mesa; ....
 */
package com.uvigo.poyectosushigo.CORE;

import com.uvigo.proyectosushigo.IU.Main;

public class Jugador {

    private final String nombre;
    private Mano mano;
    private CartasMesa mesa;

    private int [] puntuacion;
    
    /**
     * Constructor de jugador
     * 
     * @param nombre 
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Mano();
        this.mesa = new CartasMesa();

        puntuacion = new int[Main.RONDAS];
    }

    /**
     * 
     * get de nombre
     * 
     * @return string
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * 
     * get de cartas de la mesa
     * 
     * @return mesa (cartasMesa)
     */
    public CartasMesa getCartasMesa() {
        return mesa;
    }

   /**
    * 
    * Coloca en la mesa una carta escogida por int de la mano
    * 
    * @param n int 
    */
    public void EscogerCartaMesa(int n) {
        Carta escogida = mano.sacarCartaMano(n);
        mesa.colocarMesa(escogida);
    }

    /**
     * 
     * coge las cartas de la baraja
     * 
     * @param b baraja
     * @param n  int
     */
    public void CogerCartasBaraja(Baraja b, int n) {
        Carta escogida;
        for (int i = 0; i < n; i++) {
            escogida = b.darCarta();
            mano.insertarCartaMano(escogida);
        }

    }

    /**
     * get de mano de jugador
     * 
     * @return  mano
     */
    public Mano getMano() {  
        return mano;
    }

    /**
     * 
     * set de la mano para cuando pasas baraja de otro jugador
     * 
     * @param m 
     */
    public void setMano(Mano m) { // ==  setMano
        mano = m;
    }
    
    
    /**
     * 
     * inserta puntuaciones en el array de punto de los jugadores
     * 
     * @param i
     * @param puntos 
     */
    public void insertarPuntos(int i, int puntos){
        puntuacion[i] += puntos;
    }
    
    /**
     * 
     * get de los puntos de una ronda
     * 
     * @param i
     * @return 
     */
    public int getPuntosRonda(int i){
        return puntuacion[i];
    }
    
    
    /**
     * 
     * get puntos totales
     * 
     * @return puntos int
     */
    public int getPuntosTotales(){
        int puntos = 0;

        for(int i = 0; i < puntuacion.length; i++){
            puntos += puntuacion[i];
        }

        return puntos;
    }
}