package com.eidan;

import java.io.*;

/**
 * Created by eidan on 2/10/15.
 */
public class Matching {

    public static void main(String[] args) {
        try {
            long[] matches = getMatches(1000000);

            File file = new File("output.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for(int i = 0; i < matches.length; i += 2) {
                bw.append(String.valueOf(i)).append(": ").append(String.valueOf(matches[i]));
                bw.newLine();
            }
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static long[] getMatches(int n) {
        if(n % 2 != 0 || n <= 0) {
            return null;
        }

        long[] matches = new long[n+1];
        matches[2] = 1;

        for(int i = 4; i <= n; i++) {
            if(i >= 4 && i % 2 == 0) {
                if(i % 1000 == 0) {
                    System.out.println("Calculating matches for i = " + i);
                }
                long num = getNum(matches, i - 2) * 2;
                for(int j = 0; j <= i; j += 2) {
                    num += getNum(matches, j) * getNum(matches, i - j - 2);
                }
                num = num % 1000000007;
                matches[i] = num;
            }
        }

        return matches;
    }

    private static long getNum(long[] array, int index) {
        if(index >= 0 && index < array.length) {
            return array[index];
        } else {
            return 0;
        }
    }
}
