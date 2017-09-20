package com.techolution.algorithms;

import java.util.Stack;

/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 22:02
 */
public class BalancedOrNot {

    static int[] balancedOrNot(String[] expressions, int[] maxReplacements) {
        int[] result = new int[expressions.length];
        for (int i = 0; i < expressions.length; i++) {
            result[i] = balancedOrNot(expressions[i], maxReplacements[i]);
        }
        return result;
    }

    private static int balancedOrNot(String expression, int maxReplacements) {
        // >>><>>
        // <<>>
        Stack<Character> stack = new Stack<>();
        boolean balanced = true;
        for (char c : expression.toCharArray()) {
            if (c == '<') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    if (maxReplacements > 0) {
                        maxReplacements--;
                    } else {
                        balanced = false;
                        break;
                    }
                } else {
                    stack.pop();
                }
            }
        }
        if (balanced && stack.isEmpty()) {
            return 1;
        }
        return 0;
    }


}
