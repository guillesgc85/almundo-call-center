package com.almundo.callcenter.controller;

import com.almundo.callcenter.model.RequestCall;
import com.almundo.callcenter.util.ConstantsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The RequestCallController - This class content the endpoint and
 * methods to send a request call, that will attend by any employee with state free
 * @author  <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 */

@RestController
@RequestMapping(ConstantsUtil.CALL_REQUEST)
public class RequestCallController {

    /**
     * The logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(RequestCallController.class);

    /**
     * The jmsTemplate
     */
    @Autowired private JmsTemplate jmsTemplate;

    /**
     * This method send a request call to be will attend for a employee with state free
     * Post message to the message queue named "CallRequestQueue"
     * @param requestCall
     * @return Is true when the sent the call was successful
     */
    @PostMapping(ConstantsUtil.SEND_REQUEST)
    public boolean send(@RequestBody RequestCall requestCall) {
        LOG.info("Sending a RequestCall.");
        jmsTemplate.convertAndSend(ConstantsUtil.CALL_REQUEST_QUEUE, requestCall);
        return Boolean.TRUE;
    }
}
