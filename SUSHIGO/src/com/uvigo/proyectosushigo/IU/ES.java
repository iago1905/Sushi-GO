/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvigo.proyectosushigo.IU;

import com.uvigo.poyectosushigo.CORE.Jugador;
import java.util.Scanner;

public class ES {

    public static Scanner scanner = new Scanner(System.in);
    
    
   
    /**
     * leer string
     * 
     * @param mensaje
     * 
     * @return 
     */
    
    public static String pideCadena(String mensaje) {
        return pideCadena(mensaje, false);
    }
    
    /**
     * leer string
     * 
     * @param mensaje string
     * @param permiteVacia boolean
     * @return 
     */
    public static String pideCadena(String mensaje,
            boolean permiteVacia) {
        String leer;
       

        do {
            System.out.print(mensaje);
            leer = scanner.nextLine().trim();
            if (!permiteVacia && leer.length() == 0) {
                System.out.println("La cadena introducida no puede estar vacía. "
                        + "Por favor, introdúcela de nuevo.");
            }
        } while ((permiteVacia == false) && leer.length() == 0);

        return leer;
    }
    /**
     * leer jugador
     * 
     * @param i int
     * 
     * @return jugador
     */
    public static Jugador leeJugador(int i) {

        String nombre = pideCadena("Nombre del jugador " + i + " : ");
        
        return new Jugador(nombre);

    }
    
    /**
     * 
     * leer int 
     * 
     * @param msg
     * @return int
     */
    
    public static int pideNumero(String msg) {

        boolean esValido = false; // True: entero leido correctamente
        int leer = 0;

        do {
            try {
                System.out.print(msg);
                leer = Integer.parseInt(scanner.nextLine().trim());
                esValido = true;
            } catch (NumberFormatException e) {
                System.err.println("La cadena introducida no se puede "
                        + "convertir a número entero. Por favor, "
                        + "introdúcela de nuevo.");
            }
        } while (!esValido);

        return leer;
    }
    
}
