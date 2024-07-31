package com.customers.management.system.customersmicroservice.util.exception;

import com.customers.management.system.customersmicroservice.util.MessageUtil;

public class ValidationsException extends Exception{
    public ValidationsException(String code) {
        super(MessageUtil.getMessage(code));
    }

    public ValidationsException(String code, String... args) {
        super(MessageUtil.getMessage(code, args));
    }
}
