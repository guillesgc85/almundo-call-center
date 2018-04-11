package com.almundo.callcenter;

import com.almundo.callcenter.controller.RequestCallController;
import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.RequestCall;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

/**
 * The integration test cases to {@Link CallCenterIntegration} class.
 * @author <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CallCenterIntegrationTests {

	/**
	 * The applicationContext
	 */
	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * The requestCallController
	 */
	@Autowired
	private RequestCallController requestCallController;

	/**
	 * Test 11 concurrent request call, great than max the supported concurrent 10
	 */
	@Test
	public void elevenConcurretRequestCall() {

		try{
			Stream.generate(() -> new RequestCall(new Call("CALLING"))).limit(11)
					.forEach(requestCall -> requestCallController.send(requestCall));

			TimeUnit.SECONDS.sleep(30);
			assertTrue(Boolean.TRUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
			assertTrue(Boolean.FALSE);
		}
	}

	/**
	 * Test, 5 concurrent request call, less than max the supported concurrent 10
	 */
	@Test
	public void fiveConcurretRequestCall() {

		try{
			Stream.generate(() -> new RequestCall(new Call("CALLING"))).limit(5)
					.forEach(requestCall -> requestCallController.send(requestCall));

			TimeUnit.SECONDS.sleep(30);
			assertTrue(Boolean.TRUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
			assertTrue(Boolean.FALSE);
		}
	}


}
