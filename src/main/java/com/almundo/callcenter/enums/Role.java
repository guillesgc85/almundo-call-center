package com.almundo.callcenter.enums;

/**
 * The enum Role
 * @author  <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 */
public enum Role {

    /** Operator - Role*/
    OPERATOR(1),

    /** Supervisor - Role*/
    SUPERVISOR(2),

    /** Director - Role*/
    DIRECTOR(3);

    /**
     * The attention level
     */
    private int attentionLevel;
    private Role(int attentionLevel){
        this.attentionLevel = attentionLevel;
    }

    /**
     * Get attention level
     * @return
     */
    public int getAttentionLevel() {
        return attentionLevel;
    }
}
