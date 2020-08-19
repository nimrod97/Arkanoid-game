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

/**
 * @author Nimrod Gabbay
 * ID 318322484
 */

/**
 * implementation of "Level 1" class.
 */
public class Level1 implements LevelInformation {
    // fields
    private int numOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;

    /**
     * constructor.
     */
    public Level1() {
        this.numOfBalls = 1;
        this.paddleSpeed = 5;
        this.paddleWidth = 80;
        this.levelName = "Direct Hit";
        this.background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLUE);
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
        List<Ball> lst = new ArrayList<Ball>();
        for (int i = 0; i < this.numOfBalls; i++) {
            lst.add(new Ball(400, 559, 5, Color.white, 800, 600));
            lst.get(i).setVelocity(Velocity.fromAngleAndSpeed(0, 6));
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
        lst.add(new Block(new Point(380, 250), 40, 25, Color.red));
        return lst;

    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
