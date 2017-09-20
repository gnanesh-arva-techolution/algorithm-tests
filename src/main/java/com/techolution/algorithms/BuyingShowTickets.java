package com.techolution.algorithms;

/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 20:30
 */
public class BuyingShowTickets {

    public static long waitingTime(int[] tickets, int p) {
        // 26345         2 (zero based).
        // 1111    0
        long seconds = 0;
        for (int i = 0; i < tickets.length; i++) { // O(n)
            if (i < p) {
                if (tickets[i] > tickets[p]) {
                    tickets[i] = tickets[p];
                }
            } else if (i > p) {
                if (tickets[i] >= tickets[p]) {
                    tickets[i] = tickets[p] - 1;
                }
            }
            seconds += tickets[i];
        }
        // Time: O(n)
        // Space: O(1) --> In place algorithm.
        return seconds;
    }

}
