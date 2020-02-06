package com.gturedi.marketim.login2

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gturedi.marketim.util.PASSWORD
import com.gturedi.marketim.util.USERNAME
import timber.log.Timber

// created by @gturedi
class LoginViewModel2 : ViewModel() {

    var loginState = MutableLiveData<LoginState>()

    val username: ObservableField<String> = ObservableField()
    val password: ObservableField<String> = ObservableField()
    val rememberMe: ObservableField<Boolean> = ObservableField()

    //fun checkRememberMe() = preferencesService.rememberMe

    fun login() {
        //preferencesService.rememberMe = rememberMe.get()!!
        val result = username.get() == USERNAME && password.get() == PASSWORD

        Timber.i("login ${username.get()} ${password.get()} ${rememberMe.get()} $result")

        if (result) loginState.postValue(LoginState.Success())
        else if (username.get().isNullOrBlank()) loginState.postValue(LoginState.Error("kullanıcı adını boş bırakmayınız"))
        else if (password.get().isNullOrBlank()) loginState.postValue(LoginState.Error("şifre alanını boş bırakmayınız"))
        else loginState.postValue(LoginState.Error("kullanıcı/şifre hatalı"))
    }

}