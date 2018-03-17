/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psoclase;

import java.util.LinkedList;

/**
 *
 * @author dliebel
 */
public class Particle {
    
    private double fitnessValue;
    private Velocity velocity;
    private Location location;
    
    public double calFitnnes(LinkedList<Dimension> d){
       return this.fitnessValue=Utils.funtionObjetive(location,d);
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    
}
