package com.example.spi.presentation.libraries

import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class Complex() {

	private var real: Double = 0.0
	private var imag: Double = 0.0

	constructor(complex: String) : this() {
		this.real = complex.substringBefore("+", "0").toDouble()
		val imagineBuf: String = complex.substringAfter("+", "1")
		this.imag = imagineBuf.substringBefore("i", "0").toDouble()
	}

	constructor(real: Double, imag: Double) : this() {
		this.real = real
		this.imag = imag
	}

	fun copy(): Complex = Complex(real, imag)

	fun plus(d: Complex): Complex = Complex(real + d.real, imag + d.imag)

	fun minus(d: Complex): Complex = Complex(real - d.real, imag - d.imag)

	fun mult(d: Complex): Complex = Complex(real * d.real - imag * d.imag, real * d.imag + imag * d.real)

	operator fun div(d: Complex): Complex = Complex(
		(real * d.real + imag * d.imag) / (d.real * d.real + d.imag * d.imag),
		(d.real * imag - real * d.imag) / (d.real * d.real + d.imag * d.imag)
	)

	fun sqr(): Complex = Complex(real * real - imag * imag, real * imag + real * imag)

	fun reverse(): Complex = Complex(real / (real * real + imag * imag), -imag / (real * real + imag * imag))

	fun unaryMinus(): Complex = Complex(-real, -imag)

	fun abs(): Double = sqrt(real * real + imag * imag)

	fun radianAngle(): Double = if (real > 0 && imag > 0) {
		atan(imag / real)
	} else if (real < 0 && imag > 0) {
		atan(imag / real) + Math.PI
	} else {
		atan(imag / real) - Math.PI
	}

	fun pow(n: Int): Complex = Complex(
		abs().pow(n.toDouble()) * cos(n * radianAngle()),
		abs().pow(n.toDouble()) * sin(n * radianAngle())
	)

	fun root(n: Int, i: Int): Complex = Complex(
		abs().pow(1.0 / n) * cos((radianAngle() + 2 * (i - 1) * Math.PI) / n),
		abs().pow(1.0 / n) * sin((radianAngle() + 2 * (i - 1) * Math.PI) / n)
	)

	fun equals(d: Complex?): Boolean =
		if (d == null) {
			false
		} else if (d === this) {
			true
		} else {
			real == d.real && imag == d.imag
		}

	fun getReal(): Double = real

	fun getRealString(): String = real.toString()

	fun getImagString(): String = imag.toString()

	override fun equals(other: Any?): Boolean =
		if (other !is Complex) {
			false
		} else {
			real == other.real && imag == other.imag
		}

	override fun toString(): String =
		if (imag == 0.0){
			String.format("%.2f", real)
		}
		else if (imag >= 0) {
			String.format("%.2f + i*%.2f", real, imag)
		} else {
			String.format("%.2f - i*%.2f", real, -imag)
		}

	override fun hashCode(): Int {
		var result = real.hashCode()
		result = 31 * result + imag.hashCode()
		return result
	}
}