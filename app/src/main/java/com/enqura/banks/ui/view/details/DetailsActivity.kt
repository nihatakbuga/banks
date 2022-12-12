package com.enqura.banks.ui.view.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.enqura.banks.R
import com.enqura.banks.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.lifecycleOwner = this

        binding.mainProgress.root.visibility = View.VISIBLE
        binding.city.text = intent.extras!!.getString("city")
        binding.address.text = intent.extras!!.getString("address")
        binding.addressName.text = intent.extras!!.getString("address_name")
        if (intent.extras!!.getString("bank_branch")!!.isEmpty())
            binding.bankBranch.text =
                intent.extras!!.getString("district") + "/" + intent.extras!!.getString("city")
        else
            binding.bankBranch.text = intent.extras!!.getString("bank_branch")


        binding.bankCode.text = intent.extras!!.getString("bank_code")
        binding.bankType.text = intent.extras!!.getString("bank_type")
        binding.district.text = intent.extras!!.getString("district")
        binding.onLine.text = intent.extras!!.getString("on_off_line")
        binding.offSite.text = intent.extras!!.getString("on_off_site")
        binding.nearestAtm.text = intent.extras!!.getString("nearest_atm")
        binding.mainProgress.root.visibility = View.GONE


    }
}