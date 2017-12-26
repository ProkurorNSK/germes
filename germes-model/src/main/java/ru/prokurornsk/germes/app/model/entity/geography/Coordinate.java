package ru.prokurornsk.germes.app.model.entity.geography;

/**
 * Geographical coordinate of an object.
 *
 * @author prokuror
 */
public class Coordinate {
    private double x;

    private double y;

    public Coordinate(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(final double y) {
        this.y = y;
    }

}
