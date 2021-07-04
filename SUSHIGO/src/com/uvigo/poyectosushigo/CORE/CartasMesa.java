/*
* Representa las cartas que coloca el jugador en la mesa (únicamente las suyas).
* Estructura: Se utilizarán TADs adecuados para su respresentación. En concreto:Una lista de pilas 
* Funcionalidad: colocar una carta en la mesa, calcular la puntuación de las cartas de la mesa, calcular el número de rollitos, visualizar cartas de la mesa, descartar cartas de la mesa, etc
 */
package com.uvigo.poyectosushigo.CORE;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CartasMesa {

    private List<Stack<Carta>> cartasMesa; 
    int numCartas = 0;

    /**
     * 
     * Constructor de cartas mesa, es una Lista, luego añadimos pilas(pilas formadas por tipos de carta)
     * 
     */
    public CartasMesa() {
        cartasMesa = new LinkedList<>();
        numCartas = 0;
    }
    
    
    /**
     * Pasamos una carta y comprobamos si hay de ese tipo o no, y se coloca en la pila correspondiente
     * @param c 
     */
    public void colocarMesa(Carta c) {

        if (numCartas == 0) {

            Stack pila = new Stack<>();
            pila.push(c);                   //si no hay ninguna carta en la mesa creamos una nueva pila y la añadimos a la lista
            cartasMesa.add(pila);

        } else {
            int indice = 0;
            int aux = -1;
            int aux2 = 0;
            if ("Niguiri de calamar".equals(c.getNombre())
                    || "Niguiri de tortilla".equals(c.getNombre()) 
                    || "Niguiri de salmon".equals(c.getNombre())) {                
                for (Stack<Carta> pila : cartasMesa) {
                    if ("Wasabi".equals(pila.peek().getNombre())) {         //si hay un wasabi en la mesa metemos el niguiri en 
                                                                            // la misma pila que el wasabi
                        aux = indice;
                        
                    }
                    indice++;
                }
                if (aux != -1) {
                    aux2=-1;
                    cartasMesa.get(aux).push(c);
                }
            }
            
            
            
            indice = 0;
            aux = -1;
            switch (c.getNombre()) {
                case "Niguiri de calamar":
                case "Niguiri de salmon":
                case "Niguiri de tortilla":
                    
                    for (Stack<Carta> pila : cartasMesa) {
                        if (!"Wasabi".equals(pila.firstElement().getNombre())
                                && c.getNombre().equals(pila.peek().getNombre())) {//si ya hay una pila de niguiri de x
                                                                                    //metemos el nuevo en esa pila
                            aux = indice;
                        }
                        indice++;
                    }
                    if (aux != -1) {
                        cartasMesa.get(aux).push(c);
                    } else if(aux2!=-1){
                        Stack pila = new Stack<>();         //si no hay pela creada y tampoco hay una pila con un wasabi crea una nueva pila
                        pila.push(c);
                        cartasMesa.add(pila);
                    }
                    break;
                                                             // agrupar todos los makis en la misma pila    
                case "Maki de 1 rollo":
                case "Maki de 2 rollos":
                case "Maki de 3 rollos":
                    for(Stack<Carta> pila : cartasMesa) {
                       
                       
                        if (pila.peek().getNombre().equals("Maki de 1 rollo")
                                || pila.peek().getNombre().equals("Maki de 2 rollos")
                                || pila.peek().getNombre().equals("Maki de 3 rollos")){
                            aux = indice;
                        }
                        indice++;
                    }                                                              //comprueba si hay una pila creada con estas cartas
                    if (aux != -1) {
                        cartasMesa.get(aux).push(c);
                    } else {
                        Stack pila = new Stack<>();
                        pila.push(c);
                        cartasMesa.add(pila);
                    }
                    break;
                    
                case "Tempura":
                case "Gyoza":
                case "Sashimi":
                    
                    for (Stack<Carta> pila : cartasMesa) {
                        if (c.getNombre().equals(pila.peek().getNombre())) {
                            aux = indice;
                        }
                        indice++;
                    }                                                              //comprueba si hay una pila creada con estas cartas
                    if (aux != -1) {
                        cartasMesa.get(aux).push(c);
                    } else {
                        Stack pila = new Stack<>();
                        pila.push(c);
                        cartasMesa.add(pila);                                   //crea una nueva pila y laa mete en la lista
                    }
                    break;
                case "Wasabi":
                    Stack pila = new Stack<>();
                        pila.push(c);
                        cartasMesa.add(pila);                   //siempre crea una nueva pila para cada wasabi
                        break;      
            }

        }

        numCartas++;
    }
    /**
     * 
     * calcular puntuacion mesa, recorriendo las pilas de las cartas de la mesa
     * 
     * @return  int
     */
    public int calcularPuntuacionMesa() {
        int puntos = 0;
        for (Stack<Carta> pila : cartasMesa) {
            
            switch (pila.peek().getNombre()) {
                
                case "Sashimi":
                    if (pila.size() >= 3 && pila.size() < 6) {                  //si hay entre 3 y 5 sashimis se suman 10 puntos
                        puntos += 10;
                    } else if (pila.size() >= 6 && pila.size() < 9) {           //si hay entre 6 y 8 sashimis se suman 20 puntos
                        puntos += 20;
                    } else if (pila.size() >= 9) {                              //si hay mas de 9 sashimis se suman 30 puntos
                        puntos += 30;
                    }
                    break;
                    
                case "Tempura":
                    if (pila.size() >= 2 && pila.size() < 4) {                  //por cada pareja de tempuras se suman 5 puntos
                        puntos += 5;
                    } else if (pila.size() >= 4 && pila.size() < 6) {           
                        puntos += 10;
                    } else if (pila.size() >= 6 && pila.size() < 8) {
                        puntos += 15;
                    } else if (pila.size() >= 8) {
                        puntos += 20;
                    }
                    break;
                    
                case "Gyoza":
                    if (pila.size() == 1) {                                     //si hay una gyoza se suma 1 punto
                        puntos += 1;
                    } else if (pila.size() == 2) {                              //si hay dos gyozas se suman 3 puntos
                        puntos += 3;
                    } else if (pila.size() == 3) {                              //si hay tres gyozas se suman 6 puntos
                        puntos += 6;                                            
                    } else if (pila.size() == 4) {                              //si hay cuatro gyozas se suman 10 puntos
                        puntos += 10;
                    } else if (pila.size() >= 5) {                              //si hay 5 o mas gyozas se suman 15 puntos
                        puntos += 15;
                    }
                    break;
                    
                case "Niguiri de salmon":
                case "Niguiri de tortilla":
                case "Niguiri de calamar":
                    if (!"Wasabi".equals(pila.get(0).getNombre())) {
                        switch (pila.peek().getNombre()) {
                            case "Niguiri de salmon":
                                puntos += pila.size() * 2;
                                break;
                            case "Niguiri de tortilla":
                                puntos += pila.size();
                                break;
                            case "Niguiri de calamar":
                                puntos += pila.size() * 3;
                                break;
                        }

                    } else {
                        switch (pila.peek().getNombre()) {
                            case "Niguiri de salmon":
                                puntos += 6;
                                break;
                            case "Niguiri de tortilla":
                                puntos += 3;
                                break;
                            case "Niguiri de calamar":
                                puntos += 9;
                                break;
                        }

                    }

                    break;
            }

        }
        return puntos;
    }
    /**
     * 
     * Calcula el numero de rollitos 
     * 
     * @return  int
     */
    public int calcularNumeroRollitos() {
        int numMakis = 0;

        for (Stack<Carta> pila : cartasMesa) {
            if (pila.peek().getNombre().equals("Maki de 1 rollo")
                    || pila.peek().getNombre().equals("Maki de 2 rollos")
                    || pila.peek().getNombre().equals("Maki de 3 rollos")) {    
                for (Carta maki : pila) {
                    if (maki.getNombre().equals("Maki de 1 rollo")) {
                        numMakis += 1;
                    } else if (maki.getNombre().equals("Maki de 2 rollos")) {
                        numMakis += 2;
                    } else {
                        numMakis += 3;
                    }
                }

            }

        }
        return numMakis;
    }

    /**
     * Quita todas las cartas de la mesa
     * 
     */
    public void eliminar(){
        cartasMesa.clear();        
    }
    
    /**
     * 
     * tostring de cartas que hay en mesa
     * 
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Stack<Carta> pila : cartasMesa) {
            str.append("\n");                               //muestra por pantalla las cartas de la mesa
            for (Carta c : pila) {
                str.append(c.getNombre()).append("\n");
            }

        }
        return str.toString();
    }

}
