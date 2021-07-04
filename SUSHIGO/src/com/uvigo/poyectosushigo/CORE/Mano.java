/*
* Representa las cartas que tiene un jugador en la mano (las que dispone para jugar).
* Estructura: Se utilizarán TAD adecuado. 
* Funcionalidad: añadir carta a la mano, quitar carta de la mano, visualizar cartas de la mano,...
 */
package com.uvigo.poyectosushigo.CORE;

import java.util.LinkedList;
import java.util.List;

public class Mano {

    private List<Carta> mano;
    private int numeroCartas;

    /**
     * 
     * Constructor de la mano, lista
     */
    public Mano() {
        mano = new LinkedList<>();      //crea una lista de cartas

    }
    /**
     * get de numero de cartas 
     * 
     * @return int
     */
    public int NumeroCartasMano() {
        return numeroCartas;
    }
    
    /**
     * colocar carta en la mano
     * 
     * @param e 
     */
    public void insertarCartaMano(Carta e) {
        mano.add(e);                    //añade una carta a la mano
        numeroCartas++;
    }
    
    
    /**
     * 
     * quitar la carta de la mano
     * 
     * @param pos
     * @return 
     */
    public Carta sacarCartaMano(int pos) {
        Carta c = mano.get(pos);
        mano.remove(pos);               //elimina una carta a la mano
        numeroCartas--;
        return c;

    }
    /**
     * 
     * toString que enseña las cartas de una mano
     * 
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i=0;
        
        for(Carta c: mano){
            sb.append(i).append(": ").append(c.getNombre()).append("\n");   //muestra por pantalla las cartas que existen en la mano
            i++;
        }
        
        return sb.toString();
    }

}
