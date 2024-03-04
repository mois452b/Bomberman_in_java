package src.ultils;

import java.util.Random;

public class random {
    
    public static int getRandomInt( int min, int max ) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
