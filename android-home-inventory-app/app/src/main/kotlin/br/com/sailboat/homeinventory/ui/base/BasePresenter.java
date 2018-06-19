package br.com.sailboat.homeinventory.ui.base;

import android.content.Intent;

import org.jetbrains.annotations.Nullable;

import br.com.sailboat.homeinventory.ui.helper.Extras;

public abstract class BasePresenter<V extends BasePresenter.View> {

    private V view;
    private boolean firstSession = true;

    public void onViewCreated() {
        if (firstSession) {
            create();
            firstSession = false;
        } else {
            restart();
        }
    }

    public void onDestroyView() {
        setView(null);
    }

    protected void create() {}
    protected void restart() {}

    @Nullable
    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }

    protected void onResultOk(int requestCode, Intent data) {
        if (Extras.INSTANCE.hasFeedbackMessage(data) && view != null) {
            view.showFeedbackMessage(Extras.INSTANCE.getFeedbackMessage(data));
        }
    }

    protected void onResultCanceled(int requestCode, Intent data) {
        if (Extras.INSTANCE.hasErrorMessage(data) && view != null) {
            view.showErrorMessage(Extras.INSTANCE.getErrorMessage(data));
        }
    }

    protected void postResult(int requestCode, Intent data) {}


    public interface View {
        void showProgress();
        void hideProgress();
        void showFeedbackMessage(int msgId);
        void showErrorMessage(int msgId);
        void closeKeyboard();
        void disableKeyboardOnStart();
        void closeWithSuccess();
        void closeWithSuccess(int msgId);
        void closeWithFailure();
        void closeWithFailure(int msgId);
        void logError(@Nullable Exception e);
    }

}
