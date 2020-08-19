package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import listeners.ScoreTrackingListener;
import sprites.Counter;

import java.util.List;
/**
 * @author Nimrod Gabbay
 * ID 318322484
 */

/**
 * implementation of "GameFlow" class.
 */
public class GameFlow {
    // fields
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private ScoreTrackingListener score;

    /**
     * constructor.
     *
     * @param ar animation runner
     * @param ks keyboard
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new ScoreTrackingListener(new Counter(0));
    }

    /**
     * running all the levels of the game.
     *
     * @param levels levels of the game
     */
    public void runLevels(List<LevelInformation> levels) {
        Animation endScreen;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(this.animationRunner,
                    levelInfo, this.keyboardSensor, this.score);
            level.initialize();
            while (level.getRemainedBlocks().getValue() > 0
                    && level.getRemainedBalls().getValue() > 0) {
                level.run();
            }
            // there are still blocks in the game-- game over.
            if (level.getRemainedBalls().getValue() == 0) {
                endScreen = new EndScreen(this.keyboardSensor, this.score, level.getRemainedBlocks().getValue());
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        this.keyboardSensor.SPACE_KEY, endScreen));
                this.animationRunner.getGui().close();

            }

        }
        // all the levels are done and without losing-- winning the game.
        endScreen = new EndScreen(this.keyboardSensor, this.score, 0);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                this.keyboardSensor.SPACE_KEY, endScreen));
        this.animationRunner.getGui().close();
    }
}

