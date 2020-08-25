package sprites;

/**
 * @author Nimrod Gabbay
 */

/**
 * implementation of "Counter" class.
 */
public class Counter {
    //field
    private int counter = 0;

    /**
     * constructor.
     */
    public Counter() {

    }

    /**
     * constructor.
     *
     * @param num num
     */
    public Counter(int num) {
        this.counter = num;
    }

    /**
     * add number to current count.
     *
     * @param number number
     */
    public void increase(int number) {
        this.counter += number;
    }


    /**
     * subtract number from current count.
     *
     * @param number number
     */
    public void decrease(int number) {
        this.counter -= number;
    }


    /**
     * get current count.
     *
     * @return the counter
     */
    public int getValue() {
        return this.counter;

    }
}
