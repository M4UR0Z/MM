package it.mz.main;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author M4UR0Z
 * @version 1.0 - 202604.091040
 */
public class MM {
    
    private static final String VER = "ver. 1.0 - 202604.091040";
    private static final String DEFAULT_INTERVAL = "180"; 
    private static final String DEFAULT_MSG = "MM Started"; 
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] paramValues = parseParameters(args);
        
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        try {
            final Robot rbt = new Robot();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Point currentPoint = MouseInfo.getPointerInfo().getLocation();
                    rbt.mouseMove(currentPoint.x + 10, currentPoint.y);
                    try {
                        Thread.sleep(250);
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
                                      1000,  // initial delay
                                      1000 * Integer.parseInt(paramValues[1] )   // period
            );

            System.out.println(paramValues[0]);

        }
        catch(Exception ex) {
            System.err.println("Error: " + ex.getMessage());
            usage();
        }
    }

    
    /**
     * Prints usage informations
     */
    public static void usage() {
        System.out.println("MM " + VER);
        System.out.println("Usage:");
        System.out.println("[JDK_HOME/bin/] java -jar MM [-m:<text>] [-t:<seconds>] [-h]");
        System.out.println("");
        System.out.println("Without any parameter, MM will perform an horizontal mouse movement every 180 seconds,");
        System.out.println("and display the message \"MM Started\" in the terminal (stdout).");
        System.out.println("Parameters (input order is free):");
        System.out.println("-m:<text>");
        System.out.println("    Displays the provided text message instead of default \" MM started\". Empty string will result in default msg using." );
        System.out.println("    Also remember to enclose words between \"\" characters (eg. \"Agent started\")." );
        System.out.println("");
        System.out.println("-t:<second>");
        System.out.println("    Waits <seconds> between each mouse movement.");
        System.out.println("");
        System.out.println("-h|-H|-help|--help|...");
        System.out.println("    Shows (this) usage and exit.");
        
        System.exit(0);
        
    }

    private static String[] parseParameters(String[] params) {
        String[] processedParams = new String[]{DEFAULT_MSG, DEFAULT_INTERVAL};
        
        if(params.length == 0) {
            return processedParams;
        }
        
        for(String param: params) {

            if(param.startsWith("-m:")) {
                processedParams[0] = param.substring(3);
            }
            if(param.startsWith("-t:")) {
                processedParams[1] = param.substring(3);
            }
            if(param.equals("-h") || param.equals("-H") || param.contains("help") ) {
                usage();
            }
        }
        
        // ----------------- verifying parsed params... -----------------
        if(processedParams[0].isBlank() ) {
            System.err.println("Wrong value for -m option. Using default.");
            processedParams[0] = DEFAULT_MSG;
        }
        
        try {
            Integer.valueOf(processedParams[1]);
        }
        catch(NumberFormatException nfe) {
            System.err.println("Wrong value for -t option. Using default.");
            processedParams[1] = DEFAULT_INTERVAL;
        }
        // ----------------------------------------------------------------
        
        return processedParams;
    }
}