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
public class Location {
    //esto es para acomadar el poblema multidimensional
    private double[] loc;

    public Location(double[] loc) {
        super();
        this.loc = loc;
    }

    public double[] getLoc() {
        return loc;
    }

    public void setLoc(double[] loc) {
        this.loc = loc;
    }

}
