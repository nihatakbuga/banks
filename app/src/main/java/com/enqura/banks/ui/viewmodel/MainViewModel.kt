package com.enqura.banks.ui.viewmodel

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.enqura.banks.R
import com.enqura.banks.data.remote.api.ApiClient
import com.enqura.banks.data.remote.repository.BankRepository
import com.enqura.banks.util.ConnectivityReceiver


class MainViewModel(
    application: Application,
) : AndroidViewModel(application), LifecycleObserver {

    private val apiClient = ApiClient(application.applicationContext)
    private val bankRepository = BankRepository(apiClient)

    val profileIntent = MutableLiveData<Intent?>()
    val bankResponse = bankRepository.liveDataBanks

    init {
        bankRepository.getBankList()
        application.applicationContext.registerReceiver(
            ConnectivityReceiver(), IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION
            )
        )
    }

    fun showNetworkMessage(isConnected: Boolean, context: Context) {
        if (!isConnected) {
            showInternetDialog(context)
        }
    }

    private fun showInternetDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_warning)
        dialog.setCancelable(true)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        (dialog.findViewById<View>(R.id.bt_close) as AppCompatButton).setOnClickListener { v ->
            dialog.dismiss()
        }
        dialog.show()
        dialog.window!!.attributes = lp
    }

    fun showWarningDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_info)
        dialog.setCancelable(false)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        (dialog.findViewById<View>(R.id.bt_close) as AppCompatButton).setOnClickListener { v ->
            dialog.dismiss()
        }
        dialog.show()
        dialog.window!!.attributes = lp
    }


}