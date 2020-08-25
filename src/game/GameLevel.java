package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import geometry.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import levels.LevelNameIndicator;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.Collidable;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.Counter;

import java.awt.Color;
import java.util.List;

/**
 * @author Nimrod Gabbay
 */

/**
 * implementation of the "gameLevel.Game" class.
 */
public class GameLevel implements Animation {
    //fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyBoard;
    private Counter remainedBlocks;
    private Counter remainedBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;
    private ScoreTrackingListener scoreTrackingListener;


    /**
     * constructor.
     *
     * @param r           animation runner
     * @param information level
     * @param k           keyboard
     * @param score       current score
     */
    public GameLevel(AnimationRunner r, LevelInformation information, KeyboardSensor k,
                     ScoreTrackingListener score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainedBlocks = new Counter();
        this.remainedBalls = new Counter();
        this.score = new Counter();
        this.runner = r;
        this.levelInformation = information;
        this.keyBoard = k;
        this.scoreTrackingListener = score;
    }

    /**
     * adding collidble object to the list.
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * adding sprite object to the list.
     *
     * @param s sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove collidable object from the list.
     *
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);

    }

    /**
     * remove sprite object from the list.
     *
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new level: creating the Blocks, Balls, Paddle
     * ,creating hit listeners and adding them to the game according to the
     * specific level parameters.
     */
    public void initialize() {
        this.addSprite(levelInformation.getBackground());
        this.gui = this.runner.getGui();
        this.keyBoard = this.gui.getKeyboardSensor();
        BlockRemover blockRemover = new BlockRemover(this, this.remainedBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainedBalls);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreTrackingListener.getCurrentScore());
        this.addSprite(scoreIndicator);
        LevelNameIndicator levelNameIndicator = new LevelNameIndicator(this.levelInformation.levelName());
        this.addSprite(levelNameIndicator);
        // creating the balls for the specific level
        List<Ball> lstBalls = this.levelInformation.ballsCreator();
        for (int i = 0; i < lstBalls.size(); i++) {
            lstBalls.get(i).setGameEnvironment(this.environment);
            lstBalls.get(i).addToGame(this);
        }
        this.remainedBalls.increase(this.levelInformation.numberOfBalls());
        // creating the paddle and adding it to the game
        int paddleWidth = this.levelInformation.paddleWidth();
        int paddleSpeed = this.levelInformation.paddleSpeed();
        Paddle paddle = new Paddle(new Point(400 - paddleWidth / 2, 560), paddleWidth,
                20, Color.orange, this.keyBoard, paddleSpeed);
        paddle.addToGame(this);
        //creating and setting blocks in the border of the screen
        Block block1 = new Block(new Point(0, 20), 800, 20, Color.gray);
        Block block2 = new Block(new Point(0, 40), 20, 560, Color.gray);
        Block block3 = new Block(new Point(780, 40), 20, 560, Color.gray);
        // setting the "deathBlock" in the bottom of the screen in red color
        Block deathBlock = new Block(new Point(20, 580), 780, 20, Color.RED);
        // adding the blocks that in the border to the game
        Block[] blocks = new Block[] {block1, block2, block3, deathBlock};
        for (Block block : blocks) {
            block.addToGame(this);
        }
        // setting the ballRemover as a listener to deathBlock
        deathBlock.addHitListener(ballRemover);
        Block b1, b2, b3, b4, b5, b6;
        //creating the blocks of the level
        for (int i = 0; i < this.levelInformation.numberOfBlocksToRemove(); i++) {
            Block block = this.levelInformation.blocks().get(i);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            this.remainedBlocks.increase(1);
            block.addToGame(this);
        }

    }


    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.runner.run(this);


    }

    /**
     * drawing the sprites.
     *
     * @param d draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        Animation pauseScreen = new PauseScreen(this.keyBoard, this.sprites);
        if (this.keyBoard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyBoard, this.keyBoard.SPACE_KEY, pauseScreen));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // all blocks were removed
        if (this.remainedBlocks.getValue() == 0) {
            this.scoreTrackingListener.getCurrentScore().increase(100);
            this.score = scoreTrackingListener.getCurrentScore();
            this.running = false;
        }

        // all balls were removed
        if (this.remainedBalls.getValue() == 0) {
            this.running = false;
        }


    }

    /**
     * @return false if the frame should stop.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return number of blocks that left in the game
     */
    public Counter getRemainedBlocks() {
        return this.remainedBlocks;
    }

    /**
     * @return number of balls that left in the game
     */
    public Counter getRemainedBalls() {
        return this.remainedBalls;
    }
}
