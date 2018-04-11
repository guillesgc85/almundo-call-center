package com.almundo.callcenter.service.impl;

import com.almundo.callcenter.enums.CallDuration;
import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.RequestCall;
import com.almundo.callcenter.service.IDispatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * The test cases cases to {@Link DispatcherService} class.
 * @author <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class DispatcherServiceTest {

    /**
     * The dispatcher
     */
    private IDispatcher dispatcher;

    /**
     * Set up method
     */
    @Before
    public void setUp(){
        dispatcher = new DispatcherService();
    }

    /**
     * Test seven dispatch attend concurrent call
     */
    @Test
    public void dispatchSevenCall(){

        try {
            Stream.generate(() -> new RequestCall(new Call("CALLING"))).limit(7)
                    .forEach(requestCall -> dispatcher.dispatchCall(requestCall));

            TimeUnit.SECONDS.sleep(new Random().nextInt(CallDuration.DEFAULT_TIME.getMax() - CallDuration.DEFAULT_TIME.getMin()) + CallDuration.DEFAULT_TIME.getMin());

            assertTrue(Boolean.TRUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
            assertTrue(Boolean.FALSE);
        }
    }

    /**
     * Test fifteen concurrent attend call
     */
    @Test
    public void dispatchFifteenCall(){

        try {
            Stream.generate(() -> new RequestCall(new Call("CALLING"))).limit(15)
                    .forEach(requestCall -> dispatcher.dispatchCall(requestCall));

            TimeUnit.SECONDS.sleep(30);
            assertTrue(Boolean.TRUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
            assertTrue(Boolean.FALSE);
        }
    }


}
