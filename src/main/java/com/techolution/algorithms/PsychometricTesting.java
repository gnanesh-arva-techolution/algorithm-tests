package com.techolution.algorithms;

/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 22:06
 */
public class PsychometricTesting {

    static int[] jobOffers(int[] scores, int[] lowerLimits, int[] upperLimits) {
        int[] offers = new int[lowerLimits.length];
        for (int i = 0; i < lowerLimits.length; i++) {
            int lower = lowerLimits[i];
            int upper = upperLimits[i];
            int offer = 0;
            for (int score : scores) {
                if (lower <= score && score <= upper) {
                    offer++;
                }
            }
            offers[i] = offer;
        }
        return offers;
    }


}
