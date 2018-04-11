package com.almundo.callcenter.components;

import com.almundo.callcenter.model.RequestCall;
import com.almundo.callcenter.service.IDispatcher;
import com.almundo.callcenter.controller.RequestCallController;
import com.almundo.callcenter.util.ConstantsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * The class RequestCallReceiver
 * @author  <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 */
@Component
public class RequestCallReceiver {

    /**
     * The dispatcherService
     */
    @Autowired
    private IDispatcher dispatcherService;

    /**
     * The logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(RequestCallController.class);


    /**
     * This method listener and receive request calls and then invoke
     * dispatchCall method to will process the call
     * @param requestCall
     */
    @JmsListener(destination = ConstantsUtil.CALL_REQUEST_QUEUE, containerFactory =ConstantsUtil.CONTAINER_FACTORY)
    public void receiveCall(RequestCall requestCall) {
        dispatcherService.dispatchCall(requestCall);
        LOG.info("Received a RequestCall. {}", requestCall);
    }
}
