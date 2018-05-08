package br.com.sailboat.homeinventory.helper.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;

import java.text.ParseException;
import java.util.Calendar;

import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.canoe.helper.DateHelper;
import br.com.sailboat.canoe.helper.DecimalHelper;
import br.com.sailboat.canoe.helper.LogHelper;
import br.com.sailboat.canoe.helper.StringHelper;

public abstract class BasePresenterJava<T extends BasePresenterJava.View> {

    private boolean firstSession = true;
    protected T view;
    private String searchText;

    public BasePresenterJava(T view) {
        this.view = view;
    }

    public void onResume() {
        checkFirstSessionAndResume();
        postResume();
    }

    private void checkFirstSessionAndResume() {
        if (isFirstSession()) {
            onResumeFirstSession();
            setFirstSession(false);
        } else {
            onResumeAfterRestart();
        }
    }

    protected static String getStringFromView(String value) {
        if (StringHelper.isNotEmpty(value)) {
            return value;
        } else {
            return "";
        }
    }

    protected static double getDoubleFromString(String value) {
        try {
            return Double.valueOf(value);
        } catch (Exception e) {
            return 0;
        }
    }

    protected static int getIntFromString(String value) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return 0;
        }
    }

    protected static double roundValue(double value, int decimals) {
        return DecimalHelper.roundValue(value, decimals);
    }

    protected static String formatValue(double value, int decimals) {
        return DecimalHelper.formatValue(value, decimals);
    }

    protected String getString(@StringRes int id) {
        return getContext().getString(id);
    }

    protected Calendar parseStringToCalendar(String string) {
        try {
            if (StringHelper.isNotEmpty(string)) {
                return DateHelper.parseStringWithDatabaseFormatToCalendar(string);
            }
        } catch (ParseException e) {
            LogHelper.logException(e);
        }
        return null;
    }

    protected String parseCalendarToString(Calendar calendar) {
        if (calendar != null) {
            return DateHelper.parseCalendarWithDatabaseFormatToString(calendar);
        } else {
            return "";
        }
    }

    protected void executeAsyncWithProgress(final AsyncHelper.Callback callback) {
        showProgress();
        AsyncHelper.execute(new AsyncHelper.Callback() {

            @Override
            public void doInBackground() throws Exception {
                callback.doInBackground();
            }

            @Override
            public void onSuccess() {
                dismissProgress();
                callback.onSuccess();
            }

            @Override
            public void onFail(Exception e) {
                dismissProgress();
                callback.onFail(e);
            }

        });
    }

    protected void onQueryTextChange() {
    }

    protected Context getContext() {
        return view.getContext();
    }

    protected void closeKeyboard() {
        view.closeKeyboard();
    }

    protected void showMessage(String message) {
        view.showMessageDialog(message);
    }

    protected void closeActivityWithResultOk() {
        view.closeActivityWithResultOk();
    }

    protected void closeActivityWithResultOk(Intent intent) {
        view.closeActivityWithResultOk(intent);
    }

    protected void closeActivityWithResultCanceled() {
        view.closeActivityWithResultCanceled();
    }

    protected void printLogAndShowDialog(Exception e) {
        view.printLogAndShowDialog(e);
    }

    protected void showProgress() {
        getView().showProgress();
    }

    protected void dismissProgress() {
        getView().dismissProgress();
    }

    protected void hideSearchView() {
        view.setShowingSearchView(false);
        view.updateMenus();
    }

    protected void showSearchView() {
        view.setShowingSearchView(true);
        view.updateMenus();
    }

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    protected void onResumeFirstSession() {
    }

    protected void onResumeAfterRestart() {
    }

    protected void postResume() {
    }

    public void onSaveViewModelState(Bundle outState) {
    }

    public void restoreViewModel(Bundle savedInstanceState) {
    }

    public void extractExtrasFromArguments(Bundle arguments) {
    }

    public void onClickFab() {
    }

    public void onClickToolbar() {
        view.scrollToTop();
    }

    public boolean isFirstSession() {
        return firstSession;
    }

    public void setFirstSession(boolean firstSession) {
        this.firstSession = firstSession;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }




    public interface View {
        Context getContext();
        void closeKeyboard();
        void closeActivityWithResultOk();
        void closeActivityWithResultOk(Intent intent);
        void closeActivityWithResultCanceled();
        void setActivityToHideKeyboard();
        void showMessageDialog(String message);
        void showToast(String message);
        void printLogAndShowDialog(Exception e);
        void showProgress();
        void dismissProgress();
        void setShowingSearchView(boolean show);
        boolean isShowingSearchView();
        void updateMenus();
        void showEmptyView();
        void hideEmptyView();
        void showRecycler();
        void hideRecycler();
        void updateRecycler();
        void removeItemFromRecycler(int position);
        void expandToolbar();
        void setTitle(String title);
        void setSubtitle(String subtitle);
        void updateRecyclerItemChanged(int position);
        void scrollToTop();
        void scrollTo(int position);
        void runOnUi(Runnable runnable);
        void showSnackbar(String msg, int duration);
    }


}