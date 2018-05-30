package br.com.sailboat.homeinventory.ui.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.sailboat.homeinventory.App
import br.com.sailboat.homeinventory.di.AppComponent
import br.com.sailboat.homeinventory.ui.dialog.ProgressDialog


abstract class BaseFragment<P : BasePresenter> : Fragment() {

    abstract val layoutId: Int
    abstract var presenter: P
    private var progressDialog: ProgressDialog? = null

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as App).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        observeLiveData()
    }

    open fun observeLiveData() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onViewCreated()
    }

    open fun initViews() {}

    fun showProgress() {
        progressDialog = ProgressDialog()
        progressDialog?.show(fragmentManager, "PROGRESS")
    }

    fun hideProgress() {
        progressDialog?.run { dismiss() }
    }

    fun <T : View> bind(@IdRes res: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return lazy { view?.findViewById(res) as T }
    }

}