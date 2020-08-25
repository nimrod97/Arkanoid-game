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
 * implementation of "Level 4" class.
 */
public class Level4 implements LevelInformation {
    // fields
    private int numOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;

    /**
     * constructor.
     */
    public Level4() {
        this.numOfBalls = 5;
        this.paddleSpeed = 20;
        this.paddleWidth = 180;
        this.levelName = " final level";
        this.background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.white);
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
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public List<Ball> ballsCreator() {
        Random rand = new Random();
        List<Ball> lst = new ArrayList<Ball>();
        for (int i = 0; i < this.numOfBalls; i++) {
            lst.add(new Ball(400, 559, 5, Color.black, 800, 600));
            int angle = rand.nextInt(360);
            lst.get(i).setVelocity(Velocity.fromAngleAndSpeed(angle, 7));
        }
        return lst;
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
        int blockWidth = 40, blockHeight = 25;
        int x = 20;
        int y = 80;
        Block b1, b2, b3, b4, b5, b6, b7;
        while (x < 780) {
            b1 = new Block(new Point(x, y), blockWidth, blockHeight, Color.darkGray);
            lst.add(b1);
            b2 = new Block(new Point(x, y + blockHeight),
                    blockWidth, blockHeight, Color.red);
            lst.add(b2);

            b3 = new Block(new Point(x, y + 2 * blockHeight),
                    blockWidth, blockHeight, Color.yellow);
            lst.add(b3);
            b4 = new Block(new Point(x, y + 3 * blockHeight),
                    blockWidth, blockHeight, Color.green);
            lst.add(b4);

            b5 = new Block(new Point(x, y + 4 * blockHeight),
                    blockWidth, blockHeight, Color.white);
            lst.add(b5);
            b6 = new Block(new Point(x, y + 5 * blockHeight),
                    blockWidth, blockHeight, Color.pink);
            lst.add(b6);
            b7 = new Block(new Point(x, y + 6 * blockHeight),
                    blockWidth, blockHeight, Color.cyan);
            lst.add(b7);

            x = x + 40;

        }
        return lst;

    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
