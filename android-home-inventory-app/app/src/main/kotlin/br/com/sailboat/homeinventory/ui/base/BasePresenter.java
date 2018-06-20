package br.com.sailboat.homeinventory.ui.base;

import org.jetbrains.annotations.Nullable;

public abstract class BasePresenter<V extends BaseMvpContract.View> implements BaseMvpContract.Presenter {

    private V view;
    private boolean firstSession = true;

    @Override
    public void onViewCreated() {
        if (firstSession) {
            create();
            firstSession = false;
        } else {
            restart();
        }
    }

    @Override
    public void onDestroyView() {
        setView(null);
    }

    protected void create() {}
    protected void restart() {}

    @Override
    public void postResult() {}

    @Nullable
    public V getView() {
        return view;
    }

    @Override
    public void setView(@Nullable BaseMvpContract.View view) {
        this.view = (V) view;
    }
}
