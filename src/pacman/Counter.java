package pacman;

/**
 * defines counter in the game.
 *
 * @author Eden Barda
 * @version 1.0
 * @since 12.03.2023
 */
public class Counter {
    private int points;

    /**
     * constructor of counter.
     */
    public Counter() {
        this.points = 0;
    }

    /**
     * change the counter.
     *
     * @param number number of points that earned.
     */
    public void add(int number) {
        this.points += number;
    }

    /**
     * @return the count.
     */
    public int getCount() {
        return points;
    }
}
