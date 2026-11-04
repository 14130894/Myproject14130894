package com.example.foodorderingwizard

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class SideDishesFragment : Fragment(R.layout.fragment_side_dishes) {
    private val viewModel: OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cbFries = view.findViewById<CheckBox>(R.id.cbFries)
        val cbSalad = view.findViewById<CheckBox>(R.id.cbSalad)
        val cbCorn = view.findViewById<CheckBox>(R.id.cbCorn)
        val btnDone = view.findViewById<Button>(R.id.btnDoneSide)

        val listener = { side: String, isChecked: Boolean ->
            viewModel.toggleSideDish(side, isChecked)
        }

        cbFries.setOnCheckedChangeListener { _, isChecked -> listener("Fries", isChecked) }
        cbSalad.setOnCheckedChangeListener { _, isChecked -> listener("Salad", isChecked) }
        cbCorn.setOnCheckedChangeListener { _, isChecked -> listener("Corn Cup", isChecked) }

        btnDone.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}