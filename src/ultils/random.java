package src.ultils;

import java.util.Random;

public class random {
    
    public static int getRandomInt( int min, int max ) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static double distance( int x1, int y1, int x2, int y2 ) {
        return Math.sqrt( Math.pow( x2 - x1, 2 ) + Math.pow( y2 - y1, 2 ) );
    }
}
