package com.gturedi.marketim

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    /*
        you could also check my other projects and blog:
        - https://github.com/gturedi/StatefulLayout
        - https://github.com/gturedi/nytimes-api-sample
        - http://gturedi.blogspot.com/
     */

    private val preferenceService by lazy { PreferencesService(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (preferenceService.rememberMe) {
            startActivity(OrdersActivity.create(this))
            return
        }

        setContentView(R.layout.activity_login)

        etUserName.setText(USERNAME)
        etPassword.setText(PASSWORD)

        btnLogin.setOnClickListener {
            if (etUserName.text.isEmpty()) etUserName.error = str(R.string.required)
            else etUserName.error = null

            if (etPassword.text.isEmpty()) etPassword.error = str(R.string.required)
            else etPassword.error = null

            if (etUserName.text.toString() == USERNAME && etPassword.text.toString() == PASSWORD) {
                preferenceService.rememberMe = chcRememberMe.isChecked
                startActivity(OrdersActivity.create(this))
            }
        }
    }

}
