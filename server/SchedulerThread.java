package server;

import model.Room;

import java.util.Random;

public class SchedulerThread extends Thread {

    private Room kitchen;

    public SchedulerThread(Room kitchen) {
        this.kitchen = kitchen;
    }

    public void run() {

        Random random = new Random();

        while (true) {
            try {
                Thread.sleep(10000);

                if (random.nextInt(10) == 5) {
                    kitchen.setFireDetected(true);
                    System.out.println("🔥 FIRE DETECTED IN KITCHEN!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}