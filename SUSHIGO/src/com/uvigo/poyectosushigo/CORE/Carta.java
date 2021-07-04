/*
 * Representa una carta, formada por un nombre
 */
package com.uvigo.poyectosushigo.CORE;

public class Carta {

    private String nombre;
    /**
     * Constructor de carta
     * 
     * 
     * @param nombre 
     */
    public Carta(String nombre) {
        this.nombre = nombre;           //nombre de la carta
    }
    /**
     * 
     * Nombre de la carta
     * 
     * @return string 
     */
    public String getNombre() {
        return nombre;
    }

}
