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
 * ID 318322484
 */

/**
 * implementation of "Level 3" class.
 */
public class Level3 implements LevelInformation {
    // fields
    private int numOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;

    /**
     * constructor.
     */
    public Level3() {
        this.numOfBalls = 4;
        this.paddleSpeed = 17;
        this.paddleWidth = 120;
        this.levelName = "Cool Level";
        this.background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.GREEN);
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
            lst.add(new Ball(400, 559, 5, Color.white, 800, 600));
            int angle = rand.nextInt(360);
            lst.get(i).setVelocity(Velocity.fromAngleAndSpeed(angle, 6));

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
        int blockWidth = 50, blockHeight = 25;
        int x = 280;
        int y = 100;
        Block b1, b2, b3, b4, b5;
        //creating rows of blocks with different colors while they are not exceeding the width
        // and setting the blockRemover and scoreTrackingListener as listeners to each of the blocks
        while (x < 780) {
            b1 = new Block(new Point(x, y), blockWidth, blockHeight, Color.darkGray);
            lst.add(b1);
            if (x + blockWidth < 780) {
                b2 = new Block(new Point(x + blockWidth, y + blockHeight),
                        blockWidth, blockHeight, Color.red);
                lst.add(b2);
            }
            if (x + 2 * blockWidth < 780) {
                b3 = new Block(new Point(x + 2 * blockWidth, y + 2 * blockHeight),
                        blockWidth, blockHeight, Color.yellow);
                lst.add(b3);
            }
            if (x + 3 * blockWidth < 780) {
                b4 = new Block(new Point(x + 3 * blockWidth, y + 3 * blockHeight),
                        blockWidth, blockHeight, Color.blue);
                lst.add(b4);
            }
            if (x + 4 * blockWidth < 780) {
                b5 = new Block(new Point(x + 4 * blockWidth, y + 4 * blockHeight),
                        blockWidth, blockHeight, Color.pink);
                lst.add(b5);
            }


            x = x + 50;

        }
        return lst;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
