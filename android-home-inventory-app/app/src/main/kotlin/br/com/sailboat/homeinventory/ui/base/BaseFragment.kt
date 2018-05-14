package br.com.sailboat.homeinventory.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.sailboat.homeinventory.App
import br.com.sailboat.homeinventory.di.AppComponent


abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    abstract val layoutId: Int
    open lateinit var viewModel: T

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as App).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribeToViewModelEvents()
        viewModel.init()
    }

    open fun initViews() {}

    open fun subscribeToViewModelEvents() {}

}