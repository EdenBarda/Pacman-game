package pacman;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

import static java.lang.System.exit;


/**
 * defines the game.
 *
 * @author Eden Barda
 * @version 1.0
 * @since 12.03.2023
 */
public class PacmanGame {
    private static final int SLEEPER = 10;
    private static final int WIDTH = 780;
    private static final int HEIGHT = 575;
    private static boolean isPlay;

    /**
     *
     */
    private static void setIsPlay() {
        PacmanGame.isPlay = true;
    }

    /**
     * @param args the args.
     */
    public static void main(String[] args) {
        Initializer game = new Initializer();
        setIsPlay();
        Sleeper sleeper = new Sleeper();
        String temp = null;
        String[] directions = {"l", "r", "u", "d"};
        while (isPlay) {
            DrawSurface d = game.getGui().getDrawSurface();
            temp = game.getPacman().timePassed(temp);
            for (int i = 0; i < 3; i++) {
                directions[i] = (game.getGhosts().get(i).timePassed(directions[i]));
            }
            d.setColor(Color.black);
            d.fillRectangle(0, 0, WIDTH, HEIGHT);
            for (Food i : game.getFood()) {
                i.draw(d);
            }
            for (BonusFood i : game.getBonusFoods()) {
                i.draw(d);
            }
            game.getPacman().draw(d);
            for (Ghost i : game.getGhosts()) {
                i.draw(d);
            }
            for (Wall i : game.getWalls()) {
                i.draw(d);
            }
            d.setColor(Color.black);
            d.fillRectangle(WIDTH, 0, 120, HEIGHT);
            d.setColor(Color.white);
            d.drawText(WIDTH + 10, 30, "lives:", 15);
            d.setColor(Color.yellow);
            for (int i = 1; i < game.getPacman().getLives(); i++) {
                d.fillCircle(WIDTH + 19 + 22 * (i - 1), 50, 9);
            }
            d.setColor(Color.white);
            d.drawText(WIDTH + 10, 90, "score:", 15);
            d.drawText(WIDTH + 10, 120, String.format("%06d", game.getPacman().getPoints().getCount()), 20);
            if (game.getTimer().getCount() > 0) {
                if (game.getTimer().getCount() < 300) {
                    d.setColor(Color.red);
                }
                d.drawText(WIDTH + 10, 160, "edible ghosts:", 15);
                d.drawText(WIDTH + 10, 190, String.format("%04.1f", (float) game.getTimer().getCount() / 100), 20);
            }
            if (game.getPacman().getLives() == 0) {
                d.setColor(Color.black);
                d.fillRectangle(0, 0, WIDTH + 120, HEIGHT);
                d.setColor(Color.white);
                d.drawText(100, 200, "You LOSE, your score is: "
                        + game.getPacman().getPoints().getCount(), 50);
                d.drawText(340, 280, "press space to exit", 30);
                game.getGui().show(d);
                while (!(game.getGui().getKeyboardSensor().isPressed(KeyboardSensor.SPACE_KEY))) {
                    sleeper.sleepFor(SLEEPER);
                }
                exit(1);
            } else if ((game.getFood().isEmpty()) && game.getBonusFoods().isEmpty()) {
                d.setColor(Color.black);
                d.fillRectangle(0, 0, WIDTH + 120, HEIGHT);
                d.setColor(Color.white);
                d.drawText(100, 200, "You WIN, your score is: "
                        + game.getPacman().getPoints().getCount(), 50);
                d.drawText(340, 280, "press space to exit", 30);
                game.getGui().show(d);
                while (!(game.getGui().getKeyboardSensor().isPressed(KeyboardSensor.SPACE_KEY))) {
                    sleeper.sleepFor(SLEEPER);
                }
                exit(1);
            }
            game.getGui().show(d);
            sleeper.sleepFor(SLEEPER);
        }
    }
}
