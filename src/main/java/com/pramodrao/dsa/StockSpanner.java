package com.pramodrao.dsa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pramod.rao
 */
public class StockSpanner {

    private List<Integer> pricesL;
    private List<Integer> spanL;
    private int index = -1;

    public StockSpanner() {
        pricesL = new ArrayList<>(10000);
        spanL = new ArrayList<>(10000);
    }

    public int next(int price) {

        int span = 1;

        if (pricesL.isEmpty()) {
            pricesL.add(price);
            spanL.add(span);

        } else {

            int p = index;
            while (p >= 0 && price >= pricesL.get(p)) {
                span += spanL.get(p);
                p -= spanL.get(p);
            }
            pricesL.add(price);
            spanL.add(span);
        }

        index++;
        return span;
    }

    public static void main(String[] args) {
        StockSpanner obj = new StockSpanner();

        System.out.println(obj.next(100));
        System.out.println(obj.next(80));
        System.out.println(obj.next(60));
        System.out.println(obj.next(70));
        System.out.println(obj.next(60));
        System.out.println(obj.next(75));
        System.out.println(obj.next(85));
    }
}
