/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psoclase;

import java.util.LinkedList;
import static psoclase.Algoritmo.MAX_ITERATION;

/**
 *
 * @author dliebel
 */
public class PsoClase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        LinkedList dimensions = new LinkedList<Dimension>();
//        dimensions.add(new Dimension("x", 1, 4, -1, 1));
//        dimensions.add(new Dimension("y", -1, 1, -1, 1));
//        Algoritmo a = new Algoritmo(dimensions, Algoritmo.MIN, "(2.8125 - x + x * y^4)^2 + (2.25 - x + x * y^2)^2 + (1.5 - x + x*y)^2");
//       
        
//        LinkedList dimensions = new LinkedList<Dimension>();
//        dimensions.add(new Dimension("x", 0, 10, -1, 1));
//        Algoritmo a = new Algoritmo(dimensions, Algoritmo.MAX, "6x-(x^2)");
        
        LinkedList dimensions = new LinkedList<Dimension>();
        dimensions.add(new Dimension("x", -2.828, 10.41, -1, 1));
        Algoritmo a = new Algoritmo(dimensions, Algoritmo.MIN, "(x^4)-(10*(x^3))-(8*(x^2))+150*x+250");
       
        //configuracion de ejecucion
        
        Algoritmo.SWARM_SIZE = 30;
        Algoritmo.MAX_ITERATION=100;
        
        //////////// comienza el algoritmo
        //paso 1 : inicializo p y v
        a.initializeSwarm();
        //paso 2 : evaluar fitnnes
        a.evaluationFitnnesAndUpdateList();

        a.findPBest(); //comineza buscando pbest =>mejor  global  y pBestLocation =>mejor  personal

        int t = 0; //contador de generacion o iteracion
 

        while (t < MAX_ITERATION ) {  //condicion de parada 
            //determina el mejor  global y local
            a.determinePBestANDGBest(t);
            //actualiza la ubicacion y la velocidad de cada paticula en la iteracion t
            a.updateVelocityAndLocationOfParticle(t); 

          
            System.out.println("Generacion " + t + ": ");
            for (int d = 0; d < a.dimensions.size(); d++) {
                System.out.println("     Mejor " + a.dimensions.get(d).name + ": " + a.gBestLocation.getLoc()[d]);
            }
            System.out.println("     Valor: " + Utils.funtionObjetive(a.gBestLocation,dimensions));

            t++;

            a.evaluationFitnnesAndUpdateList();
        }
        /////// fin del algoritmo
        System.out.println("\nLa solución encontrada de generacion " + (t - 1) + " , la solucion es:");
        for (int d = 0; d < a.dimensions.size(); d++) {
            System.out.println("     Mejor " + a.dimensions.get(d).name + ": " + a.gBestLocation.getLoc()[d]);
        }

    }

}
