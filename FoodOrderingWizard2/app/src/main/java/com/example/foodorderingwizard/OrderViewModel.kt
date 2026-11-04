package com.example.foodorderingwizard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _order = MutableLiveData(Order())
    val order: LiveData<Order> = _order

    fun setMainMeal(meal: String) {
        val current = _order.value ?: Order()
        current.mainMeal = meal
        _order.value = current
    }

    fun toggleSideDish(side: String, isChecked: Boolean) {
        val current = _order.value ?: Order()
        if (isChecked) {
            current.sideDishes.add(side)
        } else {
            current.sideDishes.remove(side)
        }
        _order.value = current
    }

    fun setDrink(drink: String) {
        val current = _order.value ?: Order()
        current.drink = drink
        _order.value = current
    }
}