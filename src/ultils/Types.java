package src.ultils;

public class Types {
    public static enum CellType {
        EMPTY,
        WALL,
        BREAKABLE,
        BOMB,
        POWERUP
    }

    public static enum Directions {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE
    }

    public static enum BombStates {
        NORMAL,
        EXPLODING,
        EXPLODED
    }

    public static enum PowerUpType {
        LIFE,
        SPEED,
        BOMB,
        CALAVERA,
        PUSH_BOMB
    }
}
