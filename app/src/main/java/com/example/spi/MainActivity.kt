package com.example.spi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.spi.presentation.Lab1
import com.example.spi.presentation.Lab2

class MainActivity : AppCompatActivity() {

	private var button1: Button? = null
	private var button2: Button? = null
	var text: TextView? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		button1 = findViewById(R.id.bLab1)
		button2 = findViewById(R.id.bLab2)
		text = findViewById(R.id.text)
		startCalculation()
	}

	private fun startCalculation(){
		button1?.setOnClickListener {
			text?.text = Lab1().calculate()
		}

		button2?.setOnClickListener {
			text?.text = Lab2().calculate()
		}
	}
}