package levels;

import geometry.Velocity;
import sprites.Ball;
import sprites.Block;
import sprites.Sprite;

import java.util.List;
/**
 * @author Nimrod Gabbay
 * ID 318322484
 */

/**
 * interface of "LevelInformation".
 */
public interface LevelInformation {
    /**
     * @return number of balls of the level
     */
    int numberOfBalls();


    /**
     * @return list with the initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * creating the balls for this specific level with their location, size, speed...
     *
     * @return list of balls
     */
    List<Ball> ballsCreator();

    /**
     * @return speed of the paddle
     */
    int paddleSpeed();

    /**
     * @return width of the paddle
     */
    int paddleWidth();


    /**
     * return the level name that will be displayed at the top of the screen.
     *
     * @return name of the level
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();


    /**
     * @return The Blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();

    /**
     *
     * @return number of blocks that should be removed
     * before the level is considered to be "cleared"
     */
    int numberOfBlocksToRemove();
}
