/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psoclase;

import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author dliebel
 */
public class Algoritmo {

    public static int SWARM_SIZE = 30;
    public static int MAX_ITERATION = 100;
    public static int PROBLEM_DIMENSION = 2;
    public static double C1 = 2.0;
    public static double C2 = 2.0;
    public static double W_UPPERBOUND = 1.0;
    public static double W_LOWERBOUND = 0.0;

    public static final boolean MAX = true;
    public static final boolean MIN = false;

    public static boolean flagOperation = MAX;

    private Vector<Particle> swarm = new Vector<Particle>();
    private double[] pBest = new double[SWARM_SIZE];
    private Vector<Location> pBestLocation = new Vector<Location>();
    public double gBest;
    public Location gBestLocation;
    private double[] fitnessValueList = new double[SWARM_SIZE];

    Random generatorRandom = new Random();
    public LinkedList<Dimension> dimensions;

    public Algoritmo(LinkedList<Dimension> dimensions, boolean flag, String expression) {
        this.dimensions = dimensions;
        this.flagOperation = flag;
        Utils.expression = expression;
    }

    /**
     * busca el mejor pBest y pBestLocation del enjambre
     */
    public void findPBest() {

        for (int i = 0; i < SWARM_SIZE; i++) {
            pBest[i] = fitnessValueList[i];
            pBestLocation.add(swarm.get(i).getLocation());
        }
    }

    /**
     * paso 3 determina PBest y Gbest
     *
     * @param t
     */
    public void determinePBestANDGBest(int t) {
        // actualizo la mejor ubicacion (global)
        for (int i = 0; i < SWARM_SIZE; i++) {
            if (fitnessValueList[i] < pBest[i]) {
                pBest[i] = fitnessValueList[i];
                pBestLocation.set(i, swarm.get(i).getLocation());
            }
        }

        // actualizo la mejor ubicacion (personal o local)
        int bestParticleIndex = 0;
        if (this.flagOperation){
            bestParticleIndex=Utils.getMaxPos(fitnessValueList);
        }else{
           bestParticleIndex= Utils.getMinPos(fitnessValueList);
        }
        if (t == 0 || fitnessValueList[bestParticleIndex] < gBest) {
            gBest = fitnessValueList[bestParticleIndex];
            gBestLocation = swarm.get(bestParticleIndex).getLocation();
        }

    }

    /**
     * Actualiza la velocidad y la posicion de cada particula
     *
     * @param t
     */
    public void updateVelocityAndLocationOfParticle(int t) {

        double w = W_UPPERBOUND - (((double) t) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);

        for (int i = 0; i < SWARM_SIZE; i++) {
            double r1 = generatorRandom.nextDouble();
            double r2 = generatorRandom.nextDouble();

            Particle p = swarm.get(i);

            // step 3 - update velocity
            double[] newVel = new double[this.dimensions.size()]; //para actualizar la velocidad
            double[] newLoc = new double[this.dimensions.size()]; //para actualizar la ubicacion
            for (int d = 0; d < this.dimensions.size(); d++) {
                //actualiza velocidad
                newVel[d] = (w * p.getVelocity().getPos()[d])
                        + (r1 * C1) * (pBestLocation.get(i).getLoc()[d] - p.getLocation().getLoc()[d])
                        + (r2 * C2) * (gBestLocation.getLoc()[d] - p.getLocation().getLoc()[d]);
                //actualiza ubicacion
                newLoc[d] = p.getLocation().getLoc()[d] + newVel[d];
            }

            Velocity vel = new Velocity(newVel); //se instancia un nueva velocidad
            p.setVelocity(vel);//se setea la ubicaicon 

            Location loc = new Location(newLoc); //se instancia una nueva ubicaicon
            p.setLocation(loc);//se setea la ubicacion
        }

    }

    /**
     * Inicializacion de cada particula en el enjambre
     *
     * Paso 1: Inicializar posición y velocidad de cada partícula
     */
    public void initializeSwarm() {
        Particle p;
        for (int i = 0; i < SWARM_SIZE; i++) {
            p = new Particle();
            //  new double[PROBLEM_DIMENSION]
            double[] loc = new double[this.dimensions.size()];
            double[] vel = new double[this.dimensions.size()];
            for (int d = 0; d < this.dimensions.size(); d++) {

                loc[d] = this.dimensions.get(d).lMin + generatorRandom.nextDouble() * (this.dimensions.get(d).lMax - this.dimensions.get(d).lMin);

                vel[d] = this.dimensions.get(d).vMin + generatorRandom.nextDouble() * (this.dimensions.get(d).vMax - this.dimensions.get(d).vMin);

            }
            //carga aleatoriamente la ubicación dentro de un espacio definido en el Conjunto de problemas
            Location location = new Location(loc);
            //carga aleatoriamente la velocidad en el rango definido en el Conjunto de problemas
            Velocity velocity = new Velocity(vel);

            p.setLocation(location); //seteo la ubicacion generada
            p.setVelocity(velocity); //seteo la velocidad generar
            swarm.add(p); //agrego al enjambre
        }
    }

    /**
     * Paso 2 : evalua el fitnes y actualiza la lista
     */
    public void evaluationFitnnesAndUpdateList() {
        for (int i = 0; i < SWARM_SIZE; i++) {
            fitnessValueList[i] = swarm.get(i).calFitnnes(this.dimensions);
        }
    }

}
