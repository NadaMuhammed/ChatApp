package com.example.chatapp.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.chatapp.R

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var binding: DB
    var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        initObservers()
    }

    fun showLoader() {
        val builder = AlertDialog.Builder(this)
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
        val builder = AlertDialog.Builder(this)
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
        viewModel.loadingLiveData.observe(this) {
            if (it) {
                showLoader()
            } else {
                hideLoader()
            }
        }

        viewModel.errorDialogLiveData.observe(this){
            showDialog(it.title, it.message, it.posButtonTitle, it.negButtonTitle, it.onPosClick, it.onNegClick)
        }
    }

    abstract fun getLayoutId(): Int
    abstract fun initViewModel(): VM
}