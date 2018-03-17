/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psoclase;

/**
 *
 * @author dliebel
 */
public class Dimension {
    public String name;
    //limites a y b
    
  
    public double lMin;
    public double lMax;
  

    public double vMin; //velociad minima
    public double vMax; //velocidad maxima 
    /**
     * 
     * @param name nombre de la dimension
     * @param lMin ubicacion minima
     * @param lMax ubicacion maxima
     * @param vMin velocidad minima
     * @param vMax velocidad maxima
     */
    public Dimension(String name, double lMin, double lMax, double vMin, double vMax) {
        this.name = name;
        this.lMin = lMin;
        this.lMax = lMax;
        this.vMin = vMin;
        this.vMax = vMax;
    }



}
