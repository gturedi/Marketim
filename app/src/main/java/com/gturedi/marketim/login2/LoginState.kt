package com.gturedi.marketim.login2

/**
 * Created by gturedi
 */
sealed class LoginState {
    class Error(val msg: String) : LoginState()
    class Success() : LoginState()
}