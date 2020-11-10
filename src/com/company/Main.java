package com.company;

import java.util.concurrent.CyclicBarrier;

public class Main {

    private static final int NUMBER_OF_CYCLISTS = 10;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_CYCLISTS, new OpenedBarrierAction());

        for (int i = 1; i <= NUMBER_OF_CYCLISTS; i++) {
            new Thread(new Cyclist("Ciclista " + i, cyclicBarrier), "Ciclista " + i).start();
        }
    }
}
