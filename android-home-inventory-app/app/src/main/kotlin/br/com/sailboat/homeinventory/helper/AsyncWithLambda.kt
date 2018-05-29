package br.com.sailboat.homeinventory.helper

import android.os.AsyncTask

class AsyncWithLambda(
    val doInBackground: () -> Unit,
    val onSucess: () -> Unit,
    val onFail: (throwable: Throwable) -> Unit
) : AsyncTask<Void, Void, Throwable>() {

    companion object {
        fun execute(
            doInBackground: () -> Unit,
            onSucess: () -> Unit,
            onFail: (throwable: Throwable) -> Unit
        ): AsyncTask<Void, Void, Throwable> {
            return AsyncWithLambda(doInBackground, onSucess, onFail).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

    }

    override fun doInBackground(vararg params: Void?): Throwable? {
        if (!isCancelled) {
            try {
                doInBackground
            } catch (e: Exception) {
                return e
            }
        }
        return null
    }

    override fun onPostExecute(throwable: Throwable?) {
        if (!isCancelled) {
            if (throwable == null) {
                onSucess
            } else {
                onFail(throwable)
            }
        }
    }

}