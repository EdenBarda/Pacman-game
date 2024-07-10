package pacman;

import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;

/**
 * defines the components of the game.
 *
 * @author Eden Barda
 * @version 1.2
 * @since 09.03.2023
 */
public class Initializer {
    private Pacman pacman;
    private final GUI gui;
    private final Counter timer;
    private final ArrayList<Wall> walls;
    private final ArrayList<Ghost> ghosts;
    private final ArrayList<Food> food;
    private final ArrayList<BonusFood> bonusFoods;
    private static final int WIDTH = 900;
    private static final int HEIGHT = 575;

    /**
     * the constructor of game's components.
     */
    public Initializer() {
        this.gui = new GUI("Pacman - by Eden Barda", WIDTH, HEIGHT);
        this.walls = new ArrayList<>();
        this.ghosts = new ArrayList<>();
        this.food = new ArrayList<>();
        this.bonusFoods = new ArrayList<>();
        this.timer = new Counter();
        initiate();
    }

    /**
     * @return the board layout.
     */
    private static int[][] boardLayout() {
        return new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 2, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1},
                {5, 5, 5, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 5, 5, 5},
                {5, 5, 5, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 5, 5, 5},
                {1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1},
                {5, 5, 5, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 5, 5, 5},
                {5, 5, 5, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 5, 5, 5},
                {1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
                {1, 3, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
    }

    /**
     * @return get the pacman.
     */
    public Pacman getPacman() {
        return pacman;
    }

    /**
     * @return the ghosts.
     */
    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }

    /**
     * @return the walls.
     */
    public ArrayList<Wall> getWalls() {
        return walls;
    }

    /**
     * @return the food in the game.
     */
    public ArrayList<Food> getFood() {
        return food;
    }

    /**
     * @return the timer of the game.
     */
    public Counter getTimer() {
        return timer;
    }

    /**
     * @return the gui of the game.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * @return the bonus food in the game.
     */
    public ArrayList<BonusFood> getBonusFoods() {
        return bonusFoods;
    }

    /**
     * create all the members in the game by the board layout.
     */
    private void initiate() {
        int[][] boardLayout = boardLayout();
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.red);
        colors.add(Color.CYAN);
        colors.add(Color.GREEN);
        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout[i].length; j++) {
                if (boardLayout[i][j] == 0) {
                    this.food.add(new Food(new Point(j * 25 + 12.5, i * 25 + 12.5)));
                } else if (boardLayout[i][j] == 1) {
                    this.walls.add(new Wall(new Point(j * 25, i * 25), this));
                } else if (boardLayout[i][j] == 2) {
                    this.pacman = new Pacman(new Point(j * 25 + 12.5, i * 25 + 12.5), 4, this);
                } else if (boardLayout[i][j] == 3) {
                    this.ghosts.add(new Ghost(new Point(j * 25 + 12.5, i * 25 + 12.5),
                            colors.get(0), this));
                    colors.remove(0);
                } else if (boardLayout[i][j] == 4) {
                    this.bonusFoods.add(new BonusFood(new Point(j * 25 + 12.5, i * 25 + 12.5)));
                }
            }
        }
    }
}
