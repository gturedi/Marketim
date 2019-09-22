package com.gturedi.marketim.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gturedi.marketim.service.OrdersResponseState
import com.gturedi.marketim.service.OrdersService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// created by @gturedi at 9/22/19
class OrdersViewModel : ViewModel() {

    val items = MutableLiveData<OrdersResponseState>()

    fun getItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                OrdersResponseState.Data(OrdersService.getItems())
            } catch (e: Exception) {
                OrdersResponseState.Error(e)
            }
            items.postValue(result)
        }
    }

}