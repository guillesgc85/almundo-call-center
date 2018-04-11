package com.almundo.callcenter.model.employee;

import com.almundo.callcenter.enums.Role;

/**
 * The class Supervisor - call center
 * @author <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 */
public class Supervisor extends Employee{

    /**
     * The constructor
     */
    public Supervisor() {
        super(Role.SUPERVISOR);
    }

}
