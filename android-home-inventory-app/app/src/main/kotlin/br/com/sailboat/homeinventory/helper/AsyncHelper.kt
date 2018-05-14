package br.com.sailboat.homeinventory.helper

import android.os.AsyncTask

class AsyncHelper(val callback: AsyncHelper.Callback) : AsyncTask<Void, Void, Throwable>() {

    companion object {
        fun execute(callback: Callback): AsyncTask<Void, Void, Throwable> {
            return AsyncHelper(callback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

    }

    override fun doInBackground(vararg params: Void?): Throwable? {
        if (!isCancelled) {
            try {
                callback.doInBackground()
            } catch (e: Exception) {
                return e
            }
        }
        return null
    }

    override fun onPostExecute(throwable: Throwable?) {
        if (!isCancelled) {
            if (throwable == null) {
                callback.onSuccess()
            } else {
                callback.onFail(throwable)
            }
        }
    }

    interface Callback {
        fun doInBackground()
        fun onSuccess()
        fun onFail(throwable: Throwable)
    }
}