package com.almundo.callcenter.enums;

/**
 * The enum CallDuration
 * @author <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 *
 */
public enum CallDuration {

    /**
     * Default call duration
     */
    DEFAULT_TIME(5,10);

    /**
     * The min time to call duration
     */
    private int min;

    /**
     * The max time to call duration
     */
    private int max;

    /**
     * The Constuctor
     * @param min
     * @param max
     */
    private CallDuration(int min, int max){
        this.min = min;
        this.max = max;
    }

    /**
     * Get min duration
     * @return
     */
    public int getMin() {
        return min;
    }

    /**
     * Get max duration
     * @return
     */
    public int getMax() {
        return max;
    }
}
