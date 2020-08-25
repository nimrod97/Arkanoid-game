package listeners;

/**
 * @author Nimrod Gabbay
 */

/**
 * interface of "HitNotifier".
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl hitListener object
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl hitListener object
     */
    void removeHitListener(HitListener hl);
}
