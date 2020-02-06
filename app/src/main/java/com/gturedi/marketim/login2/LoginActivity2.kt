package com.gturedi.marketim.login2

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gturedi.marketim.BaseActivity
import com.gturedi.marketim.R
import com.gturedi.marketim.databinding.ActivityLogin2Binding
import com.gturedi.marketim.ui.OrdersActivity
import com.gturedi.marketim.util.toast


class LoginActivity2 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val vm = ViewModelProviders.of(this).get(LoginViewModel2::class.java)
        vm.loginState.observe(this, Observer {
            when (it) {
                is LoginState.Success -> startActivity(OrdersActivity.create(this))
                is LoginState.Error -> toast(it.msg)
            }
        })

        val binding: ActivityLogin2Binding = DataBindingUtil.setContentView(this, R.layout.activity_login2)
        binding.vm = vm
    }

}
