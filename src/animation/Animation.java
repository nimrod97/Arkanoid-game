package animation;

import biuoop.DrawSurface;
/**
 * @author Nimrod Gabbay
 */

/**
 * interface of "Animation".
 */
public interface Animation {
    /**
     * doing one frame.
     *
     * @param d draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * checking if the specific frame should stop.
     *
     * @return true if the frame should stop, false otherwise
     */
    boolean shouldStop();
}
