package pacman;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * defines the ghost in the game.
 *
 * @author Eden Barda
 * @version 1.1
 * @since 10.03.2023
 */
public class Ghost implements Visible {
    private final Point center;
    private final Point start;
    private final Color color;
    private final double radius;
    private final Counter timer;
    private final Pacman pacman;
    private final ArrayList<Wall> walls;
    private static final int WIDTH = 775;
    private final double velocity;
    private boolean isEatable;
    private boolean isDead;


    /**
     * the constructor of ghost.
     *
     * @param center      the location of the ghost.
     * @param color       the color of the ghost.
     * @param initializer the initializer of the game.
     */
    public Ghost(Point center, Color color, Initializer initializer) {
        this.center = center;
        this.start = new Point(this.center.getX(), this.center.getY());
        this.color = color;
        this.walls = initializer.getWalls();
        this.timer = initializer.getTimer();
        this.pacman = initializer.getPacman();
        this.isEatable = false;
        this.isDead = false;
        this.radius = 12.5;
        this.velocity = 3.4;
    }

    /**
     * @return the radius of the ghost.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @return the center of the ghost.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * @return the start point of the ghost.
     */
    public Point getStart() {
        return start;
    }

    /**
     * get the velocity.
     *
     * @return if eatable 2, else: the velocity.
     */
    public double getVelocity() {
        if (this.isEatable) {
            return 2;
        } else {
            return this.velocity;
        }
    }

    /**
     * set if is eatable.
     *
     * @param eatable if the ghost is eatable or not.
     */
    public void setEatable(boolean eatable) {
        this.isEatable = eatable;
    }

    /**
     * moves to the right.
     *
     * @return the new direction.
     */
    private String moveRight() {
        boolean isMove = true;
        for (Wall i : walls) {

            //if there is a wall that is in the same line (Y) and you are going to move into him, don't move.
            if ((this.center.getY() + this.radius > i.getTopLeft().getY())
                    && (this.center.getY() - this.radius < i.getBottomLeft().getY())
                    && (this.center.getX() + this.radius + getVelocity() < i.getTopRight().getX())
                    && (this.center.getX() + this.radius + getVelocity() > i.getTopLeft().getX())) {
                isMove = false;
                this.center.setX(i.getTopLeft().getX() - this.radius);
                break;
            }
        }
        if (isMove) {
            this.center.setX(this.center.getX() + getVelocity());
            return "r";
        }
        return null;
    }

    /**
     * moves to the left.
     *
     * @return the new direction.
     */
    private String moveLeft() {
        boolean isMove = true;
        for (Wall i : walls) {

            //if there is a wall that is in the same line (Y) and you are going to move into him, don't move.
            if ((this.center.getY() + this.radius > i.getTopLeft().getY())
                    && (this.center.getY() - this.radius < i.getBottomLeft().getY())
                    && (this.center.getX() - this.radius - getVelocity() > i.getTopLeft().getX())
                    && (this.center.getX() - this.radius - getVelocity() < i.getTopRight().getX())) {
                isMove = false;
                this.center.setX(i.getTopRight().getX() + this.radius);
                break;
            }
        }
        if (isMove) {
            this.center.setX(this.center.getX() - getVelocity());
            return "l";
        }
        return null;
    }

    /**
     * moves up.
     *
     * @return the new direction.
     */
    private String moveUp() {
        boolean isMove = true;
        for (Wall i : walls) {

            //if there is a wall that is in the same line (X) and you are going to move into him, don't move.
            if ((this.center.getX() + this.radius > i.getTopLeft().getX())
                    && (this.center.getX() - this.radius < i.getBottomRight().getX())
                    && (this.center.getY() - this.radius - getVelocity() > i.getTopRight().getY())
                    && (this.center.getY() - this.radius - getVelocity() < i.getBottomRight().getY())) {
                isMove = false;
                this.center.setY(i.getBottomLeft().getY() + this.radius);
                break;
            }
        }
        if (isMove) {
            this.center.setY(this.center.getY() - getVelocity());
            return "u";
        }
        return null;
    }

    /**
     * moves down.
     *
     * @return the new direction.
     */
    private String moveDown() {
        boolean isMove = true;
        for (Wall i : walls) {

            //if there is a wall that is in the same line (X) and you are going to move into him, don't move.
            if ((this.center.getX() + this.radius > i.getTopLeft().getX())
                    && (this.center.getX() - this.radius < i.getBottomRight().getX())
                    && (this.center.getY() + this.radius + getVelocity() < i.getBottomRight().getY())
                    && (this.center.getY() + this.radius + getVelocity() > i.getTopRight().getY())) {
                isMove = false;
                this.center.setY(i.getTopLeft().getY() - this.radius);
                break;
            }
        }
        if (isMove) {
            this.center.setY(this.center.getY() + getVelocity());
            return "d";
        }
        return null;
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

    /**
     * if the ghost is dead.
     */
    private void dead() {
        this.center.setY(900);
        this.isDead = true;
    }


    private void collision() {
        if ((getCenter().distance(this.pacman.getCenter()) < getRadius()
                + this.pacman.getRadius()) && (this.isEatable)) {
            dead();
            this.pacman.getPoints().add(500);
        }
    }

    @Override
    public void draw(DrawSurface s) {
        if (!this.isEatable) {
            s.setColor(this.color);
        } else {
            s.setColor(Color.LIGHT_GRAY);
        }
        s.fillCircle((int) this.center.getX(), (int) this.center.getY(), (int) this.radius);
        s.fillRectangle((int) (this.center.getX() - this.radius),
                (int) this.center.getY(), (int) this.radius * 2, (int) this.radius);
        s.setColor(Color.white);
        s.fillCircle((int) this.center.getX() + 4, (int) this.center.getY() - 3, 3);
        s.fillCircle((int) this.center.getX() - 4, (int) this.center.getY() - 3, 3);
        s.setColor(Color.black);
        s.fillCircle((int) this.center.getX() + 4, (int) this.center.getY() - 2, 2);
        s.fillCircle((int) this.center.getX() - 4, (int) this.center.getY() - 2, 2);
    }

    @Override
    public String timePassed(String direction) {
        jump();
        collision();
        if (direction != null) {
            if (direction.equals("l")) {
                direction = moveLeft();
            } else if (direction.equals("r")) {
                direction = moveRight();
            } else if (direction.equals("u")) {
                direction = moveUp();
            } else if (direction.equals("d")) {
                direction = moveDown();
            }
        } else {
            ArrayList<String> temp = new ArrayList<>();
            temp.add("l");
            temp.add("r");
            temp.add("u");
            temp.add("d");
            Random rand = new Random();
            int num = rand.nextInt(4);
            direction = temp.get(num);
            timePassed(direction);
        }
        if (this.timer.getCount() > 0) {
            this.timer.add(-1);
        } else {
            setEatable(false);
            if (isDead) {
                this.center.setX(this.start.getX());
                this.center.setY(this.start.getY());
                isDead = false;
            }
        }
        return direction;
    }
}
