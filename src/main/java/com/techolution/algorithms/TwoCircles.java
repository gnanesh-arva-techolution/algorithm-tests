package com.techolution.algorithms;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Gnanesh Arva
 * @since 20 Sep 2017 at 18:12
 */
public class TwoCircles {

    static String[] circles(String[] info) {
        String[] result = new String[info.length];
        for (int i = 0; i < info.length; i++) {
            String[] dataList = info[i].split(" ");
            int x1 = Integer.parseInt(dataList[0]);
            int y1 = Integer.parseInt(dataList[1]);
            int r1 = Integer.parseInt(dataList[2]);

            int x2 = Integer.parseInt(dataList[3]);
            int y2 = Integer.parseInt(dataList[4]);
            int r2 = Integer.parseInt(dataList[5]);

            int dis = (int) Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
            if (dis == (r1 + r2) || dis == (r2 - r1)) {
                result[i] = "Touching";
            } else if (dis == 0) {
                result[i] = "Concentric";
            } else if (dis > (r1 + r2)) {
                result[i] = "Disjoint-Outside";
            } else if (dis <= Math.abs((r2 - r1))) {
                result[i] = "Disjoint-Inside";
            } else if (dis <= (r1 + r2)) {
                result[i] = "Intersecting";
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] info = new String[sc.nextInt()];
        sc.nextLine();
        for (int i = 0; i < info.length; i++) {
            info[i] = sc.nextLine();
        }
        String[] relationShip = circles(info);
        System.out.println(Arrays.toString(relationShip));
    }

}
