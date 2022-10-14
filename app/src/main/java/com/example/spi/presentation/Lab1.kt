package com.example.spi.presentation

import com.example.spi.presentation.libraries.Complex
import com.example.spi.presentation.libraries.QBit

class Lab1 {

	fun calculate(): String {
		var result = ""
		val a0 = Complex(-0.6, 0.0)
		val a1 = Complex(0.8, 0.0)
		val q1 = QBit(a0, a1, "|0>", "|1>")
		result = q1.toString() + "\n" + q1.getValue() + "\n"
		val q2: QBit = q1.getRotatedCopy()
		result += q2.toString() + "\n" + q2.getValue() + "\n"
		val q3: QBit = q2.getRotatedCopy()
		result += q3.toString() + "\n" + q3.getValue() + "\n"
		return result
	}
}