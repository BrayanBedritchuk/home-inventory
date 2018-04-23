package br.com.sailboat.homeinventory.core;

public interface Logger {
    void debug(String tag, String message);
    void error(String tag, Throwable throwable);
}
