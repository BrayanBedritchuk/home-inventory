package br.com.sailboat.homeinventory.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import br.com.sailboat.homeinventory.R;
import br.com.sailboat.homeinventory.ui.helper.Extras;
import br.com.sailboat.homeinventory.ui.dialog.MessageDialog;
import br.com.sailboat.homeinventory.ui.dialog.ProgressDialog;
import br.com.sailboat.homeinventory.ui.helper.UIHelper;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BasePresenter.View {

    @Inject
    P presenter;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        setHasOptionsMenu(true);
        setRetainInstance(true);
        presenter.extractArgs(getActivity().getIntent());
    }

    protected void inject() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initListeners();
        presenter.setView(this);
        presenter.onViewCreated();
    }

    protected void initViews() {}

    protected void initListeners() {}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroyView();
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog();
        progressDialog.show(getFragmentManager(), "PROGRESS");
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showErrorMessage(int msgId) {
        MessageDialog.Companion.show(getFragmentManager(), R.string.oops, msgId);
    }

    @Override
    public void showFeedbackMessage(int msgId) {
        if (getView() instanceof CoordinatorLayout) {
            Snackbar.make(getView(), msgId, 5000).show();
        } else {
            Toast.makeText(getActivity(), msgId, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case Activity.RESULT_OK: {
                presenter.onResultOk(requestCode, data);
                break;
            }
            case Activity.RESULT_CANCELED: {
                presenter.onResultCanceled(requestCode, data);
                break;
            }
        }

        presenter.postResult(requestCode, data);
    }

    @Override
    public void closeKeyboard() {
        UIHelper.INSTANCE.closeKeyboard(getActivity());
    }

    @Override
    public void disableKeyboardOnStart() {
        UIHelper.INSTANCE.disableKeyboardOnStart(getActivity());
    }

    @Override
    public void closeWithSuccess() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void closeWithSuccess(int msgId) {
        Intent intent = new Intent();
        Extras.INSTANCE.putFeedbackMessage(intent, msgId);

        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void closeWithFailure() {
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    @Override
    public void logError(Exception e) {
        String msg = "An error occurred while performing the operation";

        if (e.getMessage() != null) {
            msg = e.getMessage();
        }

        Log.e("APP_ERROR_LOG", msg, e);
    }

    protected abstract int getLayoutId();

    public P getPresenter() {
        return presenter;
    }

}
