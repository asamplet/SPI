package com.example.spi.presentation

import com.example.spi.presentation.libraries.Complex
import com.example.spi.presentation.libraries.QBit
import java.util.Random

class Lab2 {

	fun calculate(): String {
		var result = ""
		val a = A()
		val b = B()
		while (b.isRotatedForm != a.isRotatedForm) {
			a.generateQbit()
			b.selectObserver(a.getQubit())
			result += "Observer predicts" + "\n"
			result += "A: " + (if (a.isRotatedForm) "rotated" else "origin") + "\n"
			result += "B: " + (if (b.isRotatedForm) "rotated" else "origin") + "\n"
		}
		result += "Common secret key" + "\n"
		result += "Key A: " + a.value + "\n"
		result += "Key B: " + b.value + "\n"
		return result
	}

	private class A {

		private val rnd: Random = Random()
		private var qbit: QBit? = null
		var isRotatedForm = false
			private set

		fun generateQbit() {
			val qbits: Array<QBit> = arrayOf(
				QBit(Complex(1.0, 0.0), Complex(0.0, 0.0), "|0>", "|1>"),
				QBit(Complex(0.0, 0.0), Complex(1.0, 0.0), "|0>", "|1>")
			)
			qbit = if (rnd.nextDouble() < 0.5) {
				qbits[0]
			} else {
				qbits[1]
			}
			if (rnd.nextDouble() < 0.5) {
				isRotatedForm = true
				qbit = qbit?.getRotatedCopy()
			} else {
				isRotatedForm = false
			}
		}

		fun getQubit(): QBit? {
			return qbit
		}

		val value: String?
			get() {
				var qbit: QBit? = getQubit()
				if (isRotatedForm) {
					qbit = qbit?.getRotatedCopy()
				}
				return qbit?.getValue()?.substring(1, 2)
			}
	}

	private class B {

		private val rnd: Random = Random()
		var isRotatedForm = false
			private set
		private var qbit: QBit? = null
		fun selectObserver(qbit: QBit?) {
			this.qbit = qbit
			isRotatedForm = rnd.nextDouble() >= 0.5
		}

		fun getQbit(): QBit? {
			return qbit
		}

		val value: String?
			get() {
				var qbit: QBit? = getQbit()
				if (isRotatedForm) {
					qbit = qbit?.getRotatedCopy()
				}
				return qbit?.getValue()?.substring(1, 2)
			}
	}
}