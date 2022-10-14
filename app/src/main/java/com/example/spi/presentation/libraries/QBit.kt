package com.example.spi.presentation.libraries

import java.util.Random
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class QBit(private val a0: Complex, private val a1: Complex, private val v0: String?, private val v1: String?) {

	private val rnd = Random()
	private val eps = 0.0000001

	private var isRotatedForm = false

	init {
		if (abs(a0.abs().pow(2.0) + a1.abs().pow(2.0) - 1) > eps) {
			throw RuntimeException(
				"Qbit not equals 1! Current = " + abs(a0.abs().pow(2.0) + a1.abs().pow(2.0))
			)
		}
	}

	override fun toString(): String =
		if (isRotatedForm) {
			"($a0)$v0` + ($a1)$v1`"
		} else {
			"($a0)$v0 + ($a1)$v1"
		}

	fun getRotatedCopy(): QBit {
		val coefficient = Complex(1.0 / sqrt(2.0), 0.0)
		val b0: Complex = a0.plus(a1).mult(coefficient)
		val b1: Complex = a0.minus(a1).mult(coefficient)
		val qbit = QBit(b0, b1, v0, v1)
		qbit.isRotatedForm = !qbit.isRotatedForm
		return qbit
	}

	fun getValue(): String? =
		if (rnd.nextDouble() < a0.abs().pow(2.0)) {
			v0
		} else {
			v1
		}
}