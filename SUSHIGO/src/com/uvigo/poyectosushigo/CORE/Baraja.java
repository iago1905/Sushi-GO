/*
* Representa la baraja del sushiGo, 94 cartas, cada una representa a una comida 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: estando la baraja desordenada, devolverá la carta situada encima del montón de cartas
 */
package com.uvigo.poyectosushigo.CORE;

import java.util.Stack;

public class Baraja {
    
    private Stack<Carta> baraja;  
 
        /**
         * Constructor, pila de cartas
         * 
         */
        public Baraja(){
        
         baraja = new Stack<>();     //pila con las cartas
 
 
        for (int i = 0; i < 5; i++) {
        baraja.push(new Carta("Niguiri de calamar"));   
 
        }
 
        for(int i = 0; i < 10; i++){
            baraja.push (new Carta("Niguiri de salmon"));
 
        }
 
        for (int i = 0; i < 5; i++) {
            baraja.push (new Carta("Niguiri de tortilla"));
 
        }
 
        for (int i = 0; i < 14; i++) {
            baraja.push (new Carta("Tempura"));
 
        }
 
        for (int i = 0; i < 14; i++) {
            baraja.push (new Carta("Sashimi"));
 
        }
 
        for (int i = 0; i < 14; i++) {
            baraja.push (new Carta("Gyoza"));
 
        }
 
        for (int i = 0; i < 6; i++) {
            baraja.push (new Carta ("Wasabi"));
 
        }
 
        for (int i = 0; i < 6; i++) {
            baraja.push (new Carta ("Maki de 1 rollo"));
 
        }
 
        for (int i = 0; i < 12; i++) {
            baraja.push (new Carta ("Maki de 2 rollos"));
 
        }
 
        for (int i = 0; i < 8; i++) {
            baraja.push (new Carta ("Maki de 3 rollos"));
 
        }
        
    }
        /**
         * Baraja las cartas
         * 
         */
        public void barajarCartas(){
        Carta arr[] = new Carta[94];    //crea un array con las cartas
        
 
 
        int aleatorio;
        int dentro = 0;
 
       do{
           aleatorio = (int) (Math.random() * 94);  
           if(arr[aleatorio] == null){  
               arr[aleatorio] = baraja.pop();   //saca una carta aleatoria del array
               dentro++;    
           }
       } while(dentro < 94);
 
 
       for(int j = 0; j < 94; j++){
           baraja.push(arr[j]);         //mete en la baraja la carta sacada anteriormente
       }
 
    }
        /**
         * Dar carta
         * 
         * @return una carta de la baraja.
         */
     public Carta darCarta(){
        return baraja.pop();    //saca una carta de la pila para darsela al jugador
    }   
 

}
