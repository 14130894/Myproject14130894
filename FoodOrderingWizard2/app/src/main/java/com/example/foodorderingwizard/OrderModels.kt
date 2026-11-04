package com.example.foodorderingwizard
data class Order(
    var mainMeal: String? = null,
    var sideDishes: MutableSet<String> = mutableSetOf(),
    var drink: String? = null
) {
    fun isComplete(): Boolean {
        return !mainMeal.isNullOrEmpty() && sideDishes.isNotEmpty() && !drink.isNullOrEmpty()
    }
}