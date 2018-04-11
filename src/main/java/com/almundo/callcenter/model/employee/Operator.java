package com.almundo.callcenter.model.employee;

import com.almundo.callcenter.enums.Role;

/**
 * The class Operator - call center
 * @author <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 */
public class Operator extends Employee{

    /**
     * The constructor
     */
    public Operator() {
        super(Role.OPERATOR);
    }

}
