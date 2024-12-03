package it.mz.main;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author M4UR0Z
 * @version 1.0 - 202411.29110925
 */
public class MM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            Robot rbt = new Robot();

            TimerTask task = new TimerTask() {
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

            new Timer().scheduleAtFixedRate(task, 1000, (1000 * 60 * 2) );
            if (args.length == 1) {
                System.out.println(args[0] + " Started.");
            } else {
                System.out.println("MM Started.");
            }
        }
        catch(Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

}
