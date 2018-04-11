package com.almundo.callcenter.model.employee;

import com.almundo.callcenter.enums.Role;

/**
 * The class Director - call center
 * @author <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 */
public class Director extends Employee{

    /**
     * The constructor
     */
    public Director() {
        super(Role.DIRECTOR);
    }

}
