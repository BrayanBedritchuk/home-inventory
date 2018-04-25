package br.com.sailboat.homeinventory.core.exception;

import java.util.List;

import br.com.sailboat.homeinventory.core.interactor.InvalidFieldRule;

public class InvalidFieldException extends Exception {

    private List<InvalidFieldRule> rules;

    public InvalidFieldException(List<InvalidFieldRule> rules) {
        this.rules = rules;
    }

    public InvalidFieldException() {
    }

    public InvalidFieldException(String s) {
        super(s);
    }

    public InvalidFieldException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidFieldException(Throwable throwable) {
        super(throwable);
    }

    public List<InvalidFieldRule> getRules() {
        return rules;
    }

    public void setRules(List<InvalidFieldRule> rules) {
        this.rules = rules;
    }
}
