package br.com.sailboat.homeinventory.core.interactor;

public interface UseCase {

    void execute(Response response);

    interface Response {
        void onSuccess();
        void onFail(Exception exception);
    }

    interface OnFail {
        void onFail(Exception exception);
    }
}