package br.com.sailboat.homeinventory.core.interactor;

public interface UseCaseWithCallback<T> {

    void execute(UseCaseWithCallback.Callback<T> callback);

    interface Callback<T> {
        void onSuccess(T result);
        void onFail(Throwable throwable);
    }
}
