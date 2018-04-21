package br.com.sailboat.homeinventory.core.interactor;

public interface UseCaseWithResponse<T> {

    void execute(Response<T> response);

    interface Response<T> {
        void onSuccess(T response);
        void onFail(Exception exception);
    }
}
