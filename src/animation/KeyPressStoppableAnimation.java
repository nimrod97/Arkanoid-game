package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Nimrod Gabbay
 * ID 318322484
 */

/**
 * implementation of "KeyPressStoppableAnimation" class.
 */
public class KeyPressStoppableAnimation implements Animation {
    // fields
    private boolean stop;
    private boolean isPressed;
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;

    /**
     * constructor.
     * @param keyboardSensor keyboard
     * @param key specific key in the keyboard
     * @param animation animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.stop = false;
        this.isPressed = true;
        this.keyboardSensor = keyboardSensor;
        this.key = key;
        this.animation = animation;
    }

    /**
     * determining if the specific key is pressed.
     * @param d draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.key) && !this.isPressed) {
            this.stop = true;
        }
        if (!this.keyboardSensor.isPressed(this.key)) {
            this.isPressed = false;
        }

    }

    /**
     *
     * @return true if the frame should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
