package com.techolution.algorithms;

/**
 * @author Gnanesh Arva
 * @since 20 Sep 2017 at 16:58
 */
public class FindTheWinner {

    static String winner(int[] andrea, int[] maria, String s) {
        int len = andrea.length;
        int totPointsMaria = 0;
        int totPointsAndrea = 0;
        for (int i = 0; i < len; i++) {
            if (EVENODD.Even.name().equalsIgnoreCase(s) && i % 2 == 0) {
                if (andrea[i] > maria[i])
                    totPointsAndrea = totPointsAndrea + andrea[i] - maria[i];
                else if (maria[i] > andrea[i])
                    totPointsMaria = totPointsMaria + maria[i] - andrea[i];
            } else if (EVENODD.Odd.name().equalsIgnoreCase(s) && i % 2 != 0) {
                if (andrea[i] > maria[i])
                    totPointsAndrea = totPointsAndrea + andrea[i] - maria[i];
                else if (maria[i] > andrea[i])
                    totPointsMaria = totPointsMaria + maria[i] - andrea[i];
            }
        }
        if (totPointsAndrea > totPointsMaria)
            return RESULT.Andrea.name();
        else if (totPointsMaria > totPointsAndrea)
            return RESULT.Maria.name();
        else
            return RESULT.Tie.name();
    }

    public static void main(String[] args) {
        System.out.printf(winner(new int[]{1, 2, 3}, new int[]{2, 1, 3}, "Even") + "\n");
        System.out.printf(winner(new int[]{1, 2, 3}, new int[]{2, 1, 3}, "Odd") + "\n");
    }

    public enum RESULT {Maria, Andrea, Tie}

    public enum EVENODD {Even, Odd}

}
