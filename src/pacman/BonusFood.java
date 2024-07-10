package pacman;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * defines the bonus food in the game.
 *
 * @author Eden Barda
 * @version 1.0
 * @since 12.03.2023
 */
public class BonusFood extends Food {

    /**
     * @param center the center of the food.
     */
    public BonusFood(Point center) {
        super(center);
    }

    @Override
    public Point getCenter() {
        return super.getCenter();
    }

    @Override
    public void draw(DrawSurface surface) {
        surface.setColor(Color.red);
        surface.fillCircle((int) getCenter().getX(), (int) getCenter().getY(), 5);
    }

    @Override
    public String timePassed(String direction) {
        return super.timePassed(direction);
    }
}
