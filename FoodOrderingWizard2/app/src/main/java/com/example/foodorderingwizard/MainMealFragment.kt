package com.example.foodorderingwizard

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class MainMealFragment : Fragment(R.layout.fragment_main_meal) {
    private val viewModel: OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rgMainMeal = view.findViewById<RadioGroup>(R.id.rgMainMeal)
        val btnDone = view.findViewById<Button>(R.id.btnDoneMain)

        rgMainMeal.setOnCheckedChangeListener { _, checkedId ->
            val meal = when (checkedId) {
                R.id.rbBurger -> "Burger"
                R.id.rbFriedChicken -> "Fried Chicken"
                R.id.rbNuggets -> "Chicken Nuggets"
                else -> ""
            }
            if (meal.isNotEmpty()) viewModel.setMainMeal(meal)
        }

        btnDone.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}