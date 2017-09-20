package com.techolution.algorithms;

/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 21:20
 */
public class ConsecutiveSum {

    static int consecutive(long num) {
        int start = 1, end = 1, sum = 1, count = 0;
        long numValue = num / 2;
        while (start <= numValue) {
            if (sum < num) {
                end += 1;
                sum += end;
            } else if (sum > num) {
                sum -= start;
                start += 1;
            } else if (sum == num) {
                sum -= start;
                start += 1;
                ++count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(consecutive(417302));
    }

}
