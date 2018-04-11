package com.almundo.callcenter.model.employee;


import com.almundo.callcenter.enums.EmployeeState;
import com.almundo.callcenter.enums.Role;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.UUID;

/**
 * Abstract class Employee
 * @author <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 */
public abstract class Employee {

    /**
     * Employee id
     */
    private String id;

    /**
     * The employee state (FREE or BUSY)
     */
    private EmployeeState employeeState;

    /**
     * Employee rol
     */
    private Role rol;

    /**
     * The constructor
     * @param rol
     */
    public Employee(final Role rol){
        this.id = UUID.randomUUID().toString();
        this.employeeState = EmployeeState.FREE;
        this.rol = rol;
    }

    /**
     * Get employee id
     * @return employee id
     */
    public String getId() {
        return id;
    }

    /**
     * Get employee state
     * @return EmployeeState
     */
    public EmployeeState getEmployeeState() {
        return employeeState;
    }

    /**
     * Get employee rol
     * @return role
     */
    public Role getRol() {
        return rol;
    }

    /**
     * Set emplyee id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set employee state
     * @param employeeState
     */
    public void setEmployeeState(EmployeeState employeeState) {
        this.employeeState = employeeState;
    }

    /**
     * Set employee rol
     * @param rol
     */
    public void setRol(Role rol) {
        this.rol = rol;
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
