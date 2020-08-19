package listeners;

import sprites.Ball;
import sprites.Block;
import sprites.Counter;
/**
 * @author Nimrod Gabbay
 * ID: 318322484
 */

/**
 * implementation of "ScoreTrackingListener" class.
 */
public class ScoreTrackingListener implements HitListener {
    //field
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter counter of score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    // adding 5 points to the total score in case of hitting
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);

    }

    /**
     * return the current score.
     * @return score
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }
}
