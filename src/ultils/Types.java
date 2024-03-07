package src.ultils;

public class Types {
    public static enum CellType {
        EMPTY,
        WALL,
        BREAKABLE,
        BOMB
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
}
