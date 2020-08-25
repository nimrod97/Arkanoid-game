package levels;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Velocity;
import sprites.Ball;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Nimrod Gabbay
 */

/**
 * implementation of "Level2" class.
 */
public class Level2 implements LevelInformation {
    // fields
    private int numOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;

    /**
     * constructor.
     */
    public Level2() {
        this.numOfBalls = 10;
        this.paddleSpeed = 10;
        this.paddleWidth = 400;
        this.levelName = "Wide Easy";
        this.background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.YELLOW);
                d.fillRectangle(0, 0, 800, 600);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    public List<Ball> ballsCreator() {
        Random rand = new Random();
        List<Ball> lst = new ArrayList<Ball>();
        for (int i = 0; i < this.numOfBalls; i++) {
            lst.add(new Ball(400, 559, 5, Color.white, 800, 600));
            int angle = 20 + 30 * i;
            lst.get(i).setVelocity(Velocity.fromAngleAndSpeed(angle, 7));
        }
        return lst;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }


    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;

    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> lst = new ArrayList<Block>();
        int blockWidth = 50, blockHeight = 25;
        int i = 1;
        int x = 20;
        int y = 200;
        while (x < 780) {
            if (i <= 2) {
                lst.add(new Block(new Point(x, y), blockWidth, blockHeight, Color.red));
            } else if (i <= 4) {
                lst.add(new Block(new Point(x, y), blockWidth, blockHeight, Color.black));
            } else if (i <= 6) {
                lst.add(new Block(new Point(x, y), blockWidth, blockHeight, Color.yellow));
            } else if (i <= 9) {
                lst.add(new Block(new Point(x, y), blockWidth, blockHeight, Color.green));
            } else if (i <= 11) {
                lst.add(new Block(new Point(x, y), blockWidth, blockHeight, Color.blue));
            } else if (i <= 13) {
                lst.add(new Block(new Point(x, y), blockWidth, blockHeight, Color.pink));
            } else if (i <= 15) {
                lst.add(new Block(new Point(x, y), blockWidth, blockHeight, Color.cyan));
            }
            i++;
            x = x + 50;

        }
        return lst;

    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
