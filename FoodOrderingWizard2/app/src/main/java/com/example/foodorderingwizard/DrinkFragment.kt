package com.example.foodorderingwizard

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class DrinkFragment : Fragment(R.layout.fragment_drink) {
    private val viewModel: OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rgDrink = view.findViewById<RadioGroup>(R.id.rgDrink)
        val btnDone = view.findViewById<Button>(R.id.btnDoneDrink)

        rgDrink.setOnCheckedChangeListener { _, checkedId ->
            val drink = when (checkedId) {
                R.id.rbCola -> "Cola"
                R.id.rbIcedTea -> "Iced Tea"
                R.id.rbWater -> "Water"
                else -> ""
            }
            if (drink.isNotEmpty()) viewModel.setDrink(drink)
        }

        btnDone.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}