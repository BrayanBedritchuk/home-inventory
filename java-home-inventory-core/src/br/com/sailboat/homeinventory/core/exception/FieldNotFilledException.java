package br.com.sailboat.homeinventory.core.exception;

public class FieldNotFilledException extends Exception {



    public FieldNotFilledException() {
    }

    public FieldNotFilledException(String s) {
        super(s);
    }

    public FieldNotFilledException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FieldNotFilledException(Throwable throwable) {
        super(throwable);
    }

}
