package com.osh.jntest.global.enums;

public enum UseType {

    NOT_USE(0),
    USE(1);

    int code;

    UseType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
