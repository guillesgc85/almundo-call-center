package com.almundo.callcenter.util;


import com.almundo.callcenter.enums.Role;
import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.RequestCall;
import com.almundo.callcenter.model.employee.Employee;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * The test cases cases to {@Link EmployeeUtil} class.
 * @author <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 */
public class EmployeeUtilTests {

    /**
     * The logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeUtilTests.class);

    /**
     * Get operator free to take a request call
     */
    @Test
    public void getOperatorToTakeCallTest(){

        Optional<Employee> employeeFree = EmployeeUtil.getEmployeeToTakeCall();

        assertTrue(employeeFree.isPresent());
        assertEquals(Role.OPERATOR, employeeFree.get().getRol());
    }

    /**
     * Get all employee free and set the state to busy
     */
    @Test
    public void getEmployeeToBusyTest(){

        Stream.generate(() -> EmployeeUtil.getEmployeeToTakeCall()).limit(10)
                .forEach(employeeFree -> {
                    Employee employee = employeeFree.get();
                    EmployeeUtil.setEmployeeStateToBusy(employee);
                    LOG.info("Employedd busy: {}" , employee);
                });

        assertFalse(EmployeeUtil.getEmployeeToTakeCall().isPresent());
    }


}
