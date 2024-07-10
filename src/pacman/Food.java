package pacman;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * defines food in the game.
 *
 * @author Eden Barda
 * @version 1.0
 * @since 12.03.2023
 */
public class Food implements Visible {
    private final Point center;

    /**
     * @param center the center of the food.
     */
    public Food(Point center) {
        this.center = center;
    }

    /**
     * @return the center of the food.
     */
    public Point getCenter() {
        return center;
    }

    @Override
    public void draw(DrawSurface surface) {
        surface.setColor(Color.orange);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), 5);
    }

    @Override
    public String timePassed(String direction) {
        return null;
    }
}
