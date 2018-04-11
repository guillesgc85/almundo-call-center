package com.almundo.callcenter.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * The RequestCall is used to get call
 * @author <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 *
 */
public class RequestCall {

    /**
     * The Attempt
     */
    private int attempt;

    /**
     * The Call
     */
    private Call call;

    /**
     * The default constructor
     */
    public RequestCall() {
        //Empty
    }

    /**
     * The constructor
     * @param attempt
     * @param call
     */
    public RequestCall(int attempt, Call call) {
        this.attempt = attempt;
        this.call = call;
    }

    /**
     * The constructor
     * @param call
     */
    public RequestCall( Call call) {
        this.call = call;
    }

    /**
     * Get the attempt
     * @return
     */
    public int getAttempt() {
        return attempt;
    }

    /**
     * Get the call
     * @return
     */
    public Call getCall() {
        return call;
    }

    /**
     * Set de attemp
     * @param attempt
     */
    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    /**
     * Set callS
     * @param call
     */
    public void setCall(Call call) {
        this.call = call;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }
}
