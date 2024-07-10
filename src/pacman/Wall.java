package pacman;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * defines the walls in the game.
 *
 * @author Eden Barda
 * @version 1.0
 * @since 08.03.2023
 */
public class Wall implements Visible {
    private final int size;
    private final double x;
    private final double y;
    private final ArrayList<Wall> walls;
    private static final int SIZE = 25;

    /**
     * the constructor of wall.
     *
     * @param upperLeft   the upper left point in the wall.
     * @param initializer the initializer of the game.
     */
    public Wall(Point upperLeft, Initializer initializer) {
        this.x = upperLeft.getX();
        this.y = upperLeft.getY();
        this.walls = initializer.getWalls();
        this.size = SIZE;
    }

    /**
     * @return top left corner.
     */
    public Point getTopLeft() {
        return new Point(this.x, this.y);
    }

    /**
     * @return bottom left corner.
     */
    public Point getBottomLeft() {
        return new Point(this.x, this.y + this.size);
    }

    /**
     * @return top right corner.
     */
    public Point getTopRight() {
        return new Point(this.x + this.size, this.y);
    }

    /**
     * @return bottom right corner.
     */
    public Point getBottomRight() {
        return new Point(this.x + this.size, this.y + this.size);
    }

    @Override
    public void draw(DrawSurface surface) {
        surface.setColor(Color.blue);
        boolean isUp = true;
        boolean isDown = true;
        boolean isLeft = true;
        boolean isRight = true;
        for (Wall i : walls) {
            if ((this.getTopLeft().isSame(i.getBottomLeft())) && (this.getTopRight().isSame(i.getBottomRight()))) {
                isUp = false;
            }
            if ((this.getBottomLeft().isSame(i.getTopLeft())) && (this.getBottomRight().isSame(i.getTopRight()))) {
                isDown = false;
            }
            if ((this.getTopLeft().isSame(i.getTopRight())) && (this.getBottomLeft().isSame(i.getBottomRight()))) {
                isLeft = false;
            }
            if ((this.getTopRight().isSame(i.getTopLeft())) && (this.getBottomRight().isSame(i.getBottomLeft()))) {
                isRight = false;
            }
        }
        if (isUp) {
            surface.drawLine((int) this.getTopLeft().getX(), (int) this.getTopLeft().getY(),
                    (int) this.getTopRight().getX(), (int) this.getTopRight().getY());
        }
        if (isDown) {
            surface.drawLine((int) this.getBottomLeft().getX(), (int) this.getBottomLeft().getY(),
                    (int) this.getBottomRight().getX(), (int) this.getBottomRight().getY());
        }
        if (isLeft) {
            surface.drawLine((int) this.getBottomLeft().getX(), (int) this.getBottomLeft().getY(),
                    (int) this.getTopLeft().getX(), (int) this.getTopLeft().getY());
        }
        if (isRight) {
            surface.drawLine((int) this.getTopRight().getX(), (int) this.getTopRight().getY(),
                    (int) this.getBottomRight().getX(), (int) this.getBottomRight().getY());
        }

    }

    @Override
    public String timePassed(String d) {
        return null;
    }
}
