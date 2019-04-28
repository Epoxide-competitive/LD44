package org.epoxide.ld44.utilities;

public enum Direction {
    NORTH(0, -1),
    NORTH_EAST(1, -1),
    EAST(1, 0),
    SOUTH_EAST(1, 1),
    SOUTH(0, 1),
    SOUTH_WEST(-1, 1),
    WEST(-1, 0),
    NORTH_WEST(-1, -1);

    int x;
    int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction[] mainDirections() {
        return new Direction[]{NORTH, EAST, SOUTH, WEST};
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
