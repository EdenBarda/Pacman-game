package pacman;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;


/**
 * Defines the Pacman, and his abilities.
 *
 * @author Eden Barda
 * @version 1.3
 * @since 06.03.2023
 */
public class Pacman implements Visible {
    private final Point center;
    private final Point start;
    private final GUI gui;
    private final double radius;
    private int lives;
    private final Counter points;
    private final Counter timer;
    private final ArrayList<Wall> walls;
    private final ArrayList<Ghost> ghosts;
    private final ArrayList<Food> food;
    private final ArrayList<BonusFood> bonusFood;
    private static final double MOVE = 3;
    private static final int WIDTH = 775;


    /**
     * Pacman is the user.
     *
     * @param center      the center of pacman.
     * @param initializer the game's initializer.
     * @param lives       the number of pacman's lives.
     */
    public Pacman(Point center, int lives, Initializer initializer) {
        this.center = center;
        this.start = new Point(this.center.getX(), this.center.getY());
        this.points = new Counter();
        this.lives = lives;
        this.radius = 12.5;
        this.gui = initializer.getGui();
        this.walls = initializer.getWalls();
        this.ghosts = initializer.getGhosts();
        this.food = initializer.getFood();
        this.bonusFood = initializer.getBonusFoods();
        this.timer = initializer.getTimer();
    }

    /**
     * @return the points.
     */
    public Counter getPoints() {
        return points;
    }

    /**
     * @return the number of live that remains.
     */
    public int getLives() {
        return lives;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * moves pacman to the right.
     */
    private void moveRight() {
        boolean isMove = true;
        for (Wall i : walls) {

            //if there is a wall that is in the same line (Y) and you are going to move into him, don't move.
            if ((this.center.getY() + this.radius > i.getTopLeft().getY())
                    && (this.center.getY() - this.radius < i.getBottomLeft().getY())
                    && (this.center.getX() + this.radius + MOVE < i.getTopRight().getX())
                    && (this.center.getX() + this.radius + MOVE > i.getTopLeft().getX())) {
                isMove = false;
                this.center.setX(i.getTopLeft().getX() - this.radius);
                break;
            }
        }
        if (isMove) {
            this.center.setX(this.center.getX() + MOVE);
        }
    }

    /**
     * moves pacman to the left.
     */
    private void moveLeft() {
        boolean isMove = true;
        for (Wall i : walls) {

            //if there is a wall that is in the same line (Y) and you are going to move into him, don't move.
            if ((this.center.getY() + this.radius > i.getTopLeft().getY())
                    && (this.center.getY() - this.radius < i.getBottomLeft().getY())
                    && (this.center.getX() - this.radius - MOVE > i.getTopLeft().getX())
                    && (this.center.getX() - this.radius - MOVE < i.getTopRight().getX())) {
                isMove = false;
                this.center.setX(i.getTopRight().getX() + this.radius);
                break;
            }
        }
        if (isMove) {
            this.center.setX(this.center.getX() - MOVE);
        }
    }

    /**
     * moves pacman up.
     */
    private void moveUp() {
        boolean isMove = true;
        for (Wall i : walls) {

            //if there is a wall that is in the same line (X) and you are going to move into him, don't move.
            if ((this.center.getX() + this.radius > i.getTopLeft().getX())
                    && (this.center.getX() - this.radius < i.getBottomRight().getX())
                    && (this.center.getY() - this.radius - MOVE > i.getTopRight().getY())
                    && (this.center.getY() - this.radius - MOVE < i.getBottomRight().getY())) {
                isMove = false;
                this.center.setY(i.getBottomLeft().getY() + this.radius);
                break;
            }
        }
        if (isMove) {
            this.center.setY(this.center.getY() - MOVE);
        }
    }

    /**
     * moves pacman down.
     */
    private void moveDown() {
        boolean isMove = true;
        for (Wall i : walls) {

            //if there is a wall that is in the same line (X) and you are going to move into him, don't move.
            if ((this.center.getX() + this.radius > i.getTopLeft().getX())
                    && (this.center.getX() - this.radius < i.getBottomRight().getX())
                    && (this.center.getY() + this.radius + MOVE < i.getBottomRight().getY())
                    && (this.center.getY() + this.radius + MOVE > i.getTopRight().getY())) {
                isMove = false;
                this.center.setY(i.getTopLeft().getY() - this.radius);
                break;
            }
        }
        if (isMove) {
            this.center.setY(this.center.getY() + MOVE);
        }
    }

    /**
     * if there is a collision, pacman lose one life and return to the start point.
     */
    private void collision() {
        if (this.lives != 0) {
            if (this.timer.getCount() == 0) {
                for (Ghost i : this.ghosts) {
                    if (i.getCenter().distance(this.center) < i.getRadius() + this.radius) {
                        this.lives -= 1;
                        Sleeper sleeper = new Sleeper();
                        sleeper.sleepFor(300);
                        if (this.lives != 0) {
                            this.center.setX(this.start.getX());
                            this.center.setY(this.start.getY());
                            for (Ghost j : this.ghosts) {
                                j.getCenter().setX(j.getStart().getX());
                                j.getCenter().setY(j.getStart().getY());
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * if pacman eat a food, the food will disrepair, and pacman will get points.
     */
    private void eat() {
        Food temp = null;
        for (Food i : this.food) {
            if (this.center.distance(i.getCenter()) < this.radius) {
                temp = i;
                break;
            }
        }
        if (temp != null) {
            this.food.remove(temp);
            this.points.add(100);
        }
    }

    /**
     * if pacman eats the bonus food.
     */
    private void eatBonus() {
        BonusFood temp = null;
        for (BonusFood i : this.bonusFood) {
            if (this.center.distance(i.getCenter()) < this.radius) {
                temp = i;
                break;
            }
        }
        if (temp != null) {
            this.bonusFood.remove(temp);
            this.points.add(100);
            for (Ghost i : this.ghosts) {
                i.setEatable(true);
            }
            this.timer.add(1000);
        }
    }

    /**
     * jump from the left side to the right or opposite.
     */
    private void jump() {
        if (this.center.getX() > WIDTH) {
            this.center.setX(0);
        }
        if (this.center.getX() < 0) {
            this.center.setX(WIDTH);
        }
    }

    @Override
    public void draw(DrawSurface surface) {
        surface.setColor(Color.yellow);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), (int) this.radius);
    }

    @Override
    public String timePassed(String direction) {
        KeyboardSensor keyboard = this.gui.getKeyboardSensor();
        collision();
        jump();
        eat();
        eatBonus();
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            return "l";
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
            return "r";
        } else if (keyboard.isPressed(KeyboardSensor.UP_KEY)) {
            moveUp();
            return "u";
        } else if (keyboard.isPressed(KeyboardSensor.DOWN_KEY)) {
            moveDown();
            return "d";
        } else if (direction != null) {
            switch (direction) {
                case "l" -> {
                    moveLeft();
                    return "l";
                }
                case "r" -> {
                    moveRight();
                    return "r";
                }
                case "u" -> {
                    moveUp();
                    return "u";
                }
                case "d" -> {
                    moveDown();
                    return "d";
                }
                default -> throw new IllegalStateException("Unexpected value: " + direction);
            }
        }
        return null;
    }
}