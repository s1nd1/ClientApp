package Client;

import java.io.Serializable;

public enum MessageType implements Serializable {
    SIGN_IN_USER,
    SIGN_UP_USER,

    ADD_BALANCE,
    DEL_BALANCE,
    GET_BALANCE,
    GET_BALANCENAME,

    ADD_EXPENSE,
    GET_EXPENSE,

    ADD_INCOME,
    GET_INCOME,

    ADD_USER,
    DEL_USER,
    GET_USER,
    UPDATE_USER,

    GET_REPORT_PIE_VALUE,

}
