/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psoclase;

import java.util.HashSet;
import java.util.LinkedList;

import java.util.Set;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 *
 * @author dliebel
 */
public class Utils {

    public static String expression = "x";

    public static int getMinPos(double[] list) {
        int pos = 0;
        double minValue = list[0];

        for (int i = 0; i < list.length; i++) {
            if (list[i] < minValue) {
                pos = i;
                minValue = list[i];
            }
        }

        return pos;
    }

    public static int getMaxPos(double[] list) {
        int pos = 0;
        double maxValue = list[0];

        for (int i = 0; i < list.length; i++) {
            if (list[i] > maxValue) {
                pos = i;
                maxValue = list[i];
            }
        }

        return pos;
    }

//    public static double decodeBtoD(int[] genes) {
//        double d = 0;
//
//        for (int j = 0; j < Algoritmo.L; j++) {
//            d += Math.pow(2, (Algoritmo.L - 1) - j) * genes[j];
//        }
//        return d;
//    }
    public static double funtionObjetive(Location location, LinkedList<Dimension> dimensions) {
        Set<String> variables = new HashSet<String>();

        for (int d = 0; d < dimensions.size(); d++) {
            variables.add(dimensions.get(d).name);
        }

        Expression e = new ExpressionBuilder(expression)
                .variables(variables)
                .build();

        for (int d = 0; d < dimensions.size(); d++) {
            e.setVariable(dimensions.get(d).name, location.getLoc()[d]);
        }

        double result = e.evaluate();

//        double result;
//        double x = location.getLoc()[0]; // the "x" part of the location
//        double y = location.getLoc()[1]; // the "y" part of the location
//
//        result = Math.pow(2.8125 - x + x * Math.pow(y, 4), 2)
//                + Math.pow(2.25 - x + x * Math.pow(y, 2), 2)
//                + Math.pow(1.5 - x + x * y, 2);
        return result;

    }

}
