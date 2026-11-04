package com.example.foodorderingwizard

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnChooseMain = findViewById<Button>(R.id.btnChooseMain)
        val btnChooseSide = findViewById<Button>(R.id.btnChooseSide)
        val btnChooseDrink = findViewById<Button>(R.id.btnChooseDrink)
        val btnOrder = findViewById<Button>(R.id.btnOrder)
        val tvCurrentSelection = findViewById<TextView>(R.id.tvCurrentSelection)

        viewModel.order.observe(this) { order ->
            tvCurrentSelection.text = "Current Selection:\nMain: ${order.mainMeal ?: "-"}\nSides: ${order.sideDishes.joinToString(", ").ifEmpty { "-" }}\nDrink: ${order.drink ?: "-"}"
        }

        btnChooseMain.setOnClickListener { replaceFragment(MainMealFragment()) }
        btnChooseSide.setOnClickListener { replaceFragment(SideDishesFragment()) }
        btnChooseDrink.setOnClickListener { replaceFragment(DrinkFragment()) }
        btnOrder.setOnClickListener { replaceFragment(ConfirmFragment()) }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}