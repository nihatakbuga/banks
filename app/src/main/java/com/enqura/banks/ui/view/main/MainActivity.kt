package com.enqura.banks.ui.view.main

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.enqura.banks.R
import com.enqura.banks.data.remote.response.BankResponse
import com.enqura.banks.databinding.ActivityListBinding
import com.enqura.banks.databinding.ActivityMainBinding
import com.enqura.banks.ui.view.details.DetailsActivity
import com.enqura.banks.ui.viewmodel.MainViewModel
import com.enqura.banks.util.AdapterListBank
import com.enqura.banks.util.ConnectivityReceiver

class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var activityListBinding: ActivityListBinding
    private var mAdapter: AdapterListBank? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        binding.lifecycleOwner = this
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = mainViewModel
        lifecycle.addObserver(mainViewModel)
        activityListBinding.mainProgress.root.visibility = View.VISIBLE

        mainViewModel.bankResponse.observe(this) {
            if (it.isEmpty()) {
                binding.includeList.root.visibility = View.GONE
                activityListBinding.mainProgress.root.visibility = View.GONE
                mainViewModel.showWarningDialog(this)
            } else {
                initComponent(applicationContext, it)
                binding.includeList.root.visibility = View.VISIBLE
                activityListBinding.mainProgress.root.visibility = View.GONE
            }
        }
    }

    private fun initComponent(context: Context, bankResponse: BankResponse) {
        activityListBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        activityListBinding.recyclerView.setHasFixedSize(true)
        mAdapter = AdapterListBank(
            context,
            bankResponse,
            R.layout.item_list_horizontal
        )
        activityListBinding.recyclerView.adapter = mAdapter

        // on item list clicked

        // on item list clicked
        mAdapter!!.setOnItemClickListener { view, obj, position ->
            val mainIntent = Intent(this@MainActivity, DetailsActivity::class.java)
            mainIntent.putExtra("city",obj.dc_SEHIR)
            mainIntent.putExtra("district",obj.dc_ILCE)
            mainIntent.putExtra("bank_branch",obj.dc_BANKA_SUBE)
            mainIntent.putExtra("bank_type",obj.dc_BANKA_TIPI)
            mainIntent.putExtra("bank_code",obj.dc_BANK_KODU)
            mainIntent.putExtra("address_name",obj.dc_ADRES_ADI)
            mainIntent.putExtra("address",obj.dc_ADRES)
            mainIntent.putExtra("on_off_line",obj.dc_ON_OFF_LINE)
            mainIntent.putExtra("on_off_site",obj.dc_ON_OFF_SITE)
            mainIntent.putExtra("nearest_atm",obj.dc_EN_YAKIM_ATM)
            startActivity(mainIntent)
            overridePendingTransition(R.anim.slide_up, R.anim.slide_up);

        }
    }

    override fun onResume() {
        super.onResume()
        this.registerReceiver(
            ConnectivityReceiver(), IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION
            )
        )
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        mainViewModel.showNetworkMessage(isConnected, this)
    }
}