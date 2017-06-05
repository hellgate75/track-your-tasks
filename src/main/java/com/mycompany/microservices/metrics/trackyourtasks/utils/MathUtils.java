package com.mycompany.microservices.metrics.trackyourtasks.utils;

/**
 * Utility Class collecting maths helpers
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
public class MathUtils {
    /**
     * Simple integer approx. progressive average corresponding to a beta formula :
     * average = ( ( (previous average * number of previous iterations) + new number ) / ( number of previous iterations + 1 ) )
     * It means that this formula calculate a progressive average of numbers coming from a sequence of previous average of numbers.
     * eg. I insert first number 2 (iterations: 1) then I want to calculate average with as second number 4 the average is 3 (2 iterations),
     * then I want to calculate average with another number 3 the average is 3 (3 iterations) and at least I want to calculate average with
     * a last number 7 the average is 4 (4 iterations). This formula has a maximum approx. of integers. With a few more time I could identify a
     * new mathematical formula.
     * @param previousAverage Previous average (double)
     * @param numberOfPreviousIterations Number of previous number used to define the average (double)
     * @param newNumber New number I want to calculate the average according to his weight (ref to numberOfPreviousIterations) in the compute (double)
     * @return New average (double)
    */
    public static double ProgressiveAverage(double previousAverage, double numberOfPreviousIterations, double newNumber) {
        return (((previousAverage*numberOfPreviousIterations)+newNumber)/(numberOfPreviousIterations+1));
    }
}
