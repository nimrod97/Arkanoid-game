/**
 * @author Nimrod Gabbay
 */

import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import levels.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * implementation of the "arkanoid game".
 */
public class Ass6Game {
    /**
     * @param args without command line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        List<LevelInformation> levels = new ArrayList<>();
        GameFlow gameFlow = new GameFlow(animationRunner, animationRunner.getGui().getKeyboardSensor());
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "1":
                    levels.add(new Level1());
                    break;
                case "2":
                    levels.add(new Level2());
                    break;
                case "3":
                    levels.add(new Level3());
                    break;
                case "4":
                    levels.add(new Level4());
                    break;
                default:
                    break;
            }
        }
        if (levels.size() == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }

        gameFlow.runLevels(levels);


    }
}

