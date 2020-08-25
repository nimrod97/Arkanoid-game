package listeners;

import game.GameLevel;
import sprites.Ball;
import sprites.Block;
import sprites.Counter;
/**
 * @author Nimrod Gabbay
 */

/**
 * implementation of "ballRemover" class.
 */
public class BallRemover implements HitListener {
    //fields
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game         game
     * @param removedBalls counter of how many balls are in the board
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    // removing the ball from the game
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);

    }
}
