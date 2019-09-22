package com.gturedi.marketim.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gturedi.marketim.service.PreferencesService
import com.gturedi.marketim.util.PASSWORD
import com.gturedi.marketim.util.USERNAME

// created by @gturedi
class LoginViewModel(val preferencesService: PreferencesService) : ViewModel() {

    fun checkRememberMe() = preferencesService.rememberMe

    fun login(user:String, pass:String, rememberMe:Boolean): Boolean {
        preferencesService.rememberMe = rememberMe
        return user == USERNAME && pass == PASSWORD
    }

    class LoginViewModelFactory(val preferencesService: PreferencesService): ViewModelProvider.NewInstanceFactory() {
        override fun <T: ViewModel> create(modelClass:Class<T>): T {
            return LoginViewModel(preferencesService) as T
        }
    }

}