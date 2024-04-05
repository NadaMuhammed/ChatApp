package com.example.chatapp.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.chatapp.R

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {
    lateinit var viewModel: VM
    lateinit var binding: DB
    var dialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = initViewModel()
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    fun showLoader() {
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.loading_dialog)
        dialog = builder.create()
        dialog?.show()
    }

    fun hideLoader() {
        dialog?.dismiss()
    }

    fun showDialog(
        title: String?,
        message: String?,
        posButtonTitle: String?,
        negButtonTitle: String?,
        onPosClick: (() -> Unit)? = null,
        onNegClick: (() -> Unit)? = null
    ) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(message)
        posButtonTitle.let {
            builder.setPositiveButton(
                it
            ) { dialog, p1 ->
                dialog.dismiss()
                onPosClick?.invoke()
            }
        }
        negButtonTitle.let {
            builder.setNegativeButton(
                it
            ) { dialog, p1 ->
                dialog.dismiss()
                onNegClick?.invoke()
            }
        }
        dialog = builder.create()
        dialog?.show()
    }

    open fun initObservers() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                showLoader()
            } else {
                hideLoader()
            }
        }

        viewModel.errorDialogLiveData.observe(viewLifecycleOwner){
            showDialog(it.title, it.message, it.posButtonTitle, it.negButtonTitle, it.onPosClick, it.onNegClick)
        }
    }

    abstract fun getLayoutId(): Int
    abstract fun initViewModel(): VM
}