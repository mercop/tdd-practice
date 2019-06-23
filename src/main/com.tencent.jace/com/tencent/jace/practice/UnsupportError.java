package com.tencent.jace.practice;

public class UnsupportError {

    String errorMsg;
    public UnsupportError(String unsupportError) {
        errorMsg = unsupportError;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnsupportError) {
            return this.errorMsg == ((UnsupportError) obj).errorMsg;
        }
        return false;
    }
}
