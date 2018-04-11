package com.almundo.callcenter.service.impl;

import com.almundo.callcenter.enums.CallDuration;
import com.almundo.callcenter.model.RequestCall;
import com.almundo.callcenter.service.IDispatcher;
import com.almundo.callcenter.model.employee.Employee;
import com.almundo.callcenter.util.ConstantsUtil;
import com.almundo.callcenter.util.EmployeeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The class DispatcherService is responsible to dispatch and attent the calls.
 * @author  <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 */
@Service
public class DispatcherService implements IDispatcher {

    /**
     * The mas numver of thread
     */
    private static final int MAX_NUMBER_THREAD = 10;

    /**
     * The executor service
     */
    private final ExecutorService executorService;

    /**
     * The logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(DispatcherService.class);

    /**
     * The jmsTemplate
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * The constructor
     */
    @Autowired
    public DispatcherService(){

        this.executorService = Executors.newFixedThreadPool(MAX_NUMBER_THREAD);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispatchCall(RequestCall requestCall){

        LOG.info("dispatchCall <{}>", requestCall);
        CompletableFuture.runAsync(() -> takeCall(requestCall), executorService);
    }

    /**
     * This method take a call request and find a free employee to attend that call,
     * if any employees aren't free, the call is again sent to CallRequestQueue
     *
     * @param requestCall
     */
    private void takeCall(final RequestCall requestCall){

        final Optional<Employee> employeeToTakeCall = EmployeeUtil.getEmployeeToTakeCall();
        if(employeeToTakeCall.isPresent()){

            attendCall(employeeToTakeCall.get(), requestCall);
        }else {

            tryAgain(requestCall);
        }
    }

    /**
     * The method attend a call with a employee and change the employee state to
     * BUSY while it lasts the call, and then when finished the call the state of
     * employee come back to FREE
     *
     * @param employee
     * @param requestCall
     */
    private void attendCall(final Employee employee, final RequestCall requestCall){

        try {

            EmployeeUtil.setEmployeeStateToBusy(employee);
            timeToAnswerCall();
        } catch (final InterruptedException e) {

            LOG.error("InterruptedException: tried to taking the call, error message {}", e.getMessage());
        }finally {

            EmployeeUtil.setEmployeeStateToFree(employee);
            LOG.info("Finished call {}", requestCall.getCall().getId());
        }
    }

    /**
     * This method send again the call to CallRequestQueue
     * @param requestCall
     */
    private void tryAgain(final RequestCall requestCall) {

        LOG.info("Try Again call {}", requestCall.getCall().getId());
        requestCall.setAttempt(requestCall.getAttempt() + 1);
        jmsTemplate.convertAndSend(ConstantsUtil.CALL_REQUEST_QUEUE, requestCall);
    }

    /**
     * This method get the thread to sleep some seconds to simulate
     * the time duration of real
     * @throws InterruptedException
     */
    private void timeToAnswerCall() throws InterruptedException {

        TimeUnit.SECONDS.sleep(randomDurationCall());
    }

    /**
     * This methods get random number between CallDuration.DEFAULT_TIME.getMin()
     * and CallDuration.DEFAULT_TIME.getMax()
     * @return the number of duration of call
     */
    private int randomDurationCall(){
        return new Random().nextInt(CallDuration.DEFAULT_TIME.getMax() - CallDuration.DEFAULT_TIME.getMin()) + CallDuration.DEFAULT_TIME.getMin();
    }

}
