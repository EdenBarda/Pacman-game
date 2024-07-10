package pacman;

import biuoop.DrawSurface;

/**
 * define visible.
 *
 * @author Eden Barda
 * @version 1.0
 * @since 09.03.2023
 */
public interface Visible {

    /**
     * draw the object.
     *
     * @param surface is the surface.
     */
    void draw(DrawSurface surface);

    /**
     * notify the object the time is passed.
     *
     * @param direction the direction of the object.
     * @return the new direction.
     */
    String timePassed(String direction);
}
