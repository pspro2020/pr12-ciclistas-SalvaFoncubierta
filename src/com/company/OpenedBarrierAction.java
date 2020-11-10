package com.company;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OpenedBarrierAction implements Runnable{

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private int counter = 0;

    @Override
    public void run() {
        if(counter == 0 ){
            System.out.printf("%s ha sido el último en llegar, empieza la ruta %s\n",
                    Thread.currentThread().getName(), LocalTime.now().format(dateTimeFormatter));
            counter++;
        } else if (counter == 1){
            System.out.printf("%s ha sido el último en llegar, vuelta a la gasolinera %s\n",
                    Thread.currentThread().getName(), LocalTime.now().format(dateTimeFormatter));
            counter++;
        } else {
            System.out.printf("%s ha sido el último en llegar, todos se van a la casa %s\n",
                    Thread.currentThread().getName(), LocalTime.now().format(dateTimeFormatter));
        }

    }

}
