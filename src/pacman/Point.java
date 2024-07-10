package pacman;

/**
 * Defines Point is 2D world.
 *
 * @author Eden Barda
 * @version 1.0
 * @since 06.03.2023
 */
public class Point {
    private double x;
    private double y;

    /**
     * @param x parameter in the 2D game.
     * @param y parameter in the 2D game.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x parameter.
     */
    public double getX() {
        return x;
    }

    /**
     * @return y parameter.
     */
    public double getY() {
        return y;
    }

    /**
     * changes the x.
     *
     * @param x parameter.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * changes the y.
     *
     * @param y parameter.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @param other point.
     * @return the distance between this point and the other one.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getY() - other.getY(), 2));
    }

    /**
     * @param other point.
     * @return if same point.
     */
    public boolean isSame(Point other) {
        return (this.getX() == other.getX()) && (this.getY() == other.getY());
    }
}
