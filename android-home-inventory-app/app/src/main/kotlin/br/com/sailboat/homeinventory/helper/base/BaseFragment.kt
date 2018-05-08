package br.com.sailboat.homeinventory.helper.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.sailboat.homeinventory.App
import br.com.sailboat.homeinventory.helper.dialog.ProgressDialog
import br.com.sailboat.homeinventory.helper.injection.AppComponent


abstract class BaseFragment<T : BasePresenter> : Fragment(), BasePresenter.View {

    private var progressDialog: ProgressDialog? = null
    private var firstSession = true

    abstract val layoutId: Int
    open lateinit var presenter: T

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as App).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        if (firstSession) {
            presenter.onCreateView()
        } else {
            presenter.onRestartView()
        }
    }

    open fun initViews() {}

    override fun onResume() {
        super.onResume()
        firstSession = false
    }

    override fun showProgress() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog()
            progressDialog!!.show(fragmentManager, "TAG")
        }
    }

    override fun hideProgress() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

}