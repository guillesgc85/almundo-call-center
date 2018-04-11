package com.almundo.callcenter.service;

import com.almundo.callcenter.model.RequestCall;

/**
 * The interface IDispatcher call request
 * @author <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 *
 */
public interface IDispatcher {

    /**
     * this method is used to dispatch and attend requestCall
     *
     * @param requestCall
     */
    void dispatchCall(RequestCall requestCall);
}
