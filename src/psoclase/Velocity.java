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
public class Velocity {
    //esto es para acomadar el poblema multidimensional
    private double[] vel;

    public Velocity(double[] vel) {
        super();
        this.vel = vel;
    }

    public double[] getPos() {
        return vel;
    }

    public void setPos(double[] vel) {
        this.vel = vel;
    }

}
