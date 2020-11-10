package com.company;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Cyclist implements Runnable{

    private String name;
    private CyclicBarrier cyclicBarrier;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Cyclist(String name, CyclicBarrier cyclicBarrier) {
        this.name = name;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        try {
            goToFuelStation();
        } catch (InterruptedException e) {
            System.out.printf("%s se ha interrumpido mientras iba a la gasolinera\n", name);
            return;
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            System.out.printf("%s se ha interrumpido mientras esperaba en la gasolinera\n", name);
            return;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s no espera más en la gasolinera\n", name);
        }

        try {
            startRunning();
        } catch (InterruptedException e) {
            System.out.printf("%s se ha interrumpido mientras hacía la etapa\n", name);
            return;
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            System.out.printf("%s se ha interrumpido mientras esperaba en la venta\n", name);
            return;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s no espera más en la venta\n", name);
        }

        try {
            returnToFuelStation();
        } catch (InterruptedException e) {
            System.out.printf("%s se ha interrumpido mientras volvía a la gasolinera\n", name);
            return;
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            System.out.printf("%s se ha interrumpido mientras esperaba en la gasolinera para volver\n", name);
            return;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s no espera más en la gasolinera para volver\n", name);
        }

        try {
            goHome();
        } catch (InterruptedException e) {
            System.out.printf("%s se ha interrumpido mientras volvía a la casa\n", name);
        }
    }

    private void goToFuelStation() throws InterruptedException{
        System.out.printf("%s ha salido de su casa %s\n", name, LocalTime.now().format(dateTimeFormatter));
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3) + 1);
        System.out.printf("%s ha llegado a la gasolinera %s\n", name, LocalTime.now().format(dateTimeFormatter));
    }

    private void startRunning() throws InterruptedException{
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 5);
        System.out.printf("%s ha llegado a la venta %s\n", name, LocalTime.now().format(dateTimeFormatter));
    }

    private void returnToFuelStation() throws InterruptedException{
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 5);
        System.out.printf("%s ha llegado a la gasolinera para volver %s\n", name, LocalTime.now().format(dateTimeFormatter));
    }

    private void goHome() throws InterruptedException{
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3) + 1);
        System.out.printf("%s está ya en casa %s\n", name, LocalTime.now().format(dateTimeFormatter));
    }

}
