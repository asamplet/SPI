package com.example.spi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.spi.presentation.Lab1
import com.example.spi.presentation.Lab2
import com.example.spi.presentation.Lab3
import com.example.spi.presentation.Lab32
import com.example.spi.presentation.Lab42

class MainActivity : AppCompatActivity() {

	private var button1: Button? = null
	private var button2: Button? = null
	private var button3: Button? = null
	private var button32: Button? = null
	private var button4: Button? = null
	var text: TextView? = null
	@SuppressLint("MissingInflatedId")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		button1 = findViewById(R.id.bLab1)
		button2 = findViewById(R.id.bLab2)
		button3 = findViewById(R.id.bLab3)
		button32 = findViewById(R.id.bLab32)
		button4 = findViewById(R.id.bLab4)
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

		button3?.setOnClickListener {
			text?.text = Lab3().calculate()
		}

		button32?.setOnClickListener {
			text?.text = Lab32().calculate()
		}

		button4?.setOnClickListener {
			text?.text = Lab42().calculate()
		}
	}
}