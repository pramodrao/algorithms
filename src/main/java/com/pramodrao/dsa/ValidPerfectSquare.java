package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class ValidPerfectSquare {
    public static boolean isPerfectSquare(int num) {
        if (num == 1 || num == 4) return true;
        if (num < 9) return false;

        int low = 0; int hi = num/2;
        int sqroot = (low + hi)/2;
        long square = (long)sqroot * (long)sqroot;
        while (low <= hi) {
            if (square == num) return true;
            if (square > num || square < 0) hi = sqroot-1;
            else low = sqroot+1;

            sqroot = (low + hi)/2;
            square = (long)sqroot * (long)sqroot;
        }

        return false;
    }


    public static void main(String[] args) {
//        System.out.println(isPerfectSquare(2147483647));
        System.out.println(isPerfectSquare(2147395600));
    }
}
