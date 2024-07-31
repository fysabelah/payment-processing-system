package com.payment.system.customer_service.util.exception;

import com.payment.system.customer_service.util.MessageUtil;

public class ValidationsException extends Exception {
    public ValidationsException(String code) {
        super(MessageUtil.getMessage(code));
    }
}
