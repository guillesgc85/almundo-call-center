package com.almundo.callcenter.util;

import com.almundo.callcenter.enums.EmployeeState;
import com.almundo.callcenter.model.employee.Director;
import com.almundo.callcenter.model.employee.Employee;
import com.almundo.callcenter.model.employee.Operator;
import com.almundo.callcenter.model.employee.Supervisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * The class EmployeeUtil content the methods to need
 * for attention call
 * @author  <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 */
public class EmployeeUtil {

    /**
     * The number of operator can taken a call
     */
    private static final int NUMBER_OPERATOR = 5;

    /**
     * The number of supervisor can taken a call
     */
    private static final int NUMBER_SUPERVISOR = 3;

    /**
     * The number of director can taken a call
     */
    private static final int NUMBER_DIRECTOR = 2;

    /**
     * The logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeUtil.class);

    /**
     * Employee list
     */
    private static final List<Employee> employeeList = new ArrayList();

    /**
     * Block using to add employee to employeeList
     */
    static {
        for (int i = 0; i < NUMBER_OPERATOR; i++) {
            addOperator();
        }
        for (int i = 0; i < NUMBER_SUPERVISOR; i++) {
            addSupervisor();
        }
        for (int i = 0; i < NUMBER_DIRECTOR; i++) {
            addDirecctor();
        }
        orderEmployeeList();
    }

    /**
     * Add director to employeeList
     */
    private static void addDirecctor(){
        employeeList.add(new Director());
    }

    /**
     * Add supervisor to employeeList
     */
    private static void addSupervisor(){
        employeeList.add(new Supervisor());
    }

    /**
     * Add operator to employeeList
     */
    private static void addOperator(){
        employeeList.add(new Operator());
    }

    /**
     * Find a return the employee available to take a call
     * @return
     */
    public static synchronized Optional<Employee> getEmployeeToTakeCall(){

        return  employeeList.stream().filter(e -> e.getEmployeeState().equals(EmployeeState.FREE)).findFirst();
    }

    /**
     *  Order the employeeList by attention level
     */
    private static void orderEmployeeList() {
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee em1, Employee em2) {
                return em1.getRol().getAttentionLevel() - em2.getRol().getAttentionLevel();
            }
        });
    }

    /**
     * Set the employeeState to Busy
     * @param employee
     */
    public static void setEmployeeStateToBusy(final Employee employee){
        employee.setEmployeeState(EmployeeState.BUSY);
    }

    /**
     * * Set the employeeState to free
     * @param employee
     */
    public static void setEmployeeStateToFree(final Employee employee){
        employee.setEmployeeState(EmployeeState.FREE);
    }



}
