package com.gturedi.marketim.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.gturedi.marketim.BaseActivity
import com.gturedi.marketim.R
import com.gturedi.marketim.service.PreferencesService
import com.gturedi.marketim.util.PASSWORD
import com.gturedi.marketim.util.USERNAME
import com.gturedi.marketim.util.str
import com.gturedi.marketim.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber

class LoginActivity : BaseActivity() {

    /*
        you could also check my other projects and blog:
        - https://github.com/gturedi/StatefulLayout
        - https://github.com/gturedi/nytimes-api-sample
        - http://gturedi.blogspot.com/
     */

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this, LoginViewModel.LoginViewModelFactory(preferenceService))
            .get(LoginViewModel::class.java)
    }

    private val preferenceService by lazy { PreferencesService(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val checkRememberMe = viewModel.checkRememberMe()
        Timber.i("checkRememberMe:" + checkRememberMe)
        if (checkRememberMe) {
            startActivity(OrdersActivity.create(this))
            return
        }

        supportActionBar?.hide()
        setContentView(R.layout.activity_login)

        etUserName.setText(USERNAME)
        etPassword.setText(PASSWORD)

        btnLogin.setOnClickListener {
            Timber.i("btnLoginClicked")
            if (etUserName.text.isEmpty()) etUserName.error = str(R.string.required)
            else etUserName.error = null

            if (etPassword.text.isEmpty()) etPassword.error = str(R.string.required)
            else etPassword.error = null

            if (viewModel.login(
                    etUserName.text.toString(),
                    etPassword.text.toString(),
                    chcRememberMe.isChecked
                )
            ) {
                startActivity(OrdersActivity.create(this))
            } else {
                toast(R.string.wrongUserOrPass)
            }
        }
    }

}
