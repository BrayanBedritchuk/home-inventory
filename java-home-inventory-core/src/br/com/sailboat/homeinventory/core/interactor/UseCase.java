package br.com.sailboat.homeinventory.core.interactor;

public interface UseCase<T> {
    T execute() throws Exception;
}