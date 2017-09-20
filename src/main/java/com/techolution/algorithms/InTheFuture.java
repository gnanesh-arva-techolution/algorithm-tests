package com.techolution.algorithms;

/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 21:36
 */
public class InTheFuture {

    static int minNum(int A, int K, int P) {
        if (A >= K) {
            return -1;
        }
        int days = (P / (K - A)) + 1;
        return days;
    }

}
