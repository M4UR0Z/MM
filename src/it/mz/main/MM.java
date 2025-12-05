package it.mz.main;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author M4UR0Z
 * @version 1.0 - 202511.2110000
 */
public class MM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        try {
            final Robot rbt = new Robot();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Point currentPoint = MouseInfo.getPointerInfo().getLocation();
                    rbt.mouseMove(currentPoint.x + 10, currentPoint.y);
                    try {
                        Thread.sleep(100);
                    }
                    catch(InterruptedException iex){
                        System.err.println("Error: " + iex.getMessage());
                        Thread.currentThread().interrupt();
                    }
                    rbt.mouseMove(currentPoint.x, currentPoint.y);
                }
            };

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(task, 
                                      1000,             // delay
                                      (1000 * 10 * 3)   // period
            );

            if (args.length == 1) {
                System.out.println(args[0] + " Started.");
            } 
            else {
                System.out.println("MM Started.");
            }
        }
        catch(Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

}
