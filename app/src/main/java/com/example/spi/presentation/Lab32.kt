package com.example.spi.presentation

class Lab32 {

	val default = mutableListOf<Func2>()
	val table = listOf(
		Func2(x2 = false, x1 = false, y2 = false, y1 = false),
		Func2(x2 = false, x1 = true, y2 = true, y1 = true),
		Func2(x2 = true, x1 = false, y2 = true, y1 = false),
		Func2(x2 = true, x1 = true, y2 = false, y1 = true),
	)
	var resTable = emptyList<Func2>()
	val n = 16

	fun calculate(): String {
		var result = ""
		fillDef(16)
		resTable = xorTable(default, table, 16, 4)
		val result1 = jumpT(resTable, 16, 16)
		result += "default \n" + printFunc(default) + "\n"
		result += "table \n" + printFunc(table) + "\n"
		result += "result \n" + printFunc(resTable) + "\n"
		for (i in 0 until n) {
			for (j in 0 until n) {
				result += result1[i][j].toString() + " "
			}
			result += "\n"
		}
		return result
	}

	fun jumpT(a: List<Func2>, n: Int, m: Int): List<List<Int>> {
		val result = mutableListOf<List<Int>>()
		val aNum = mutableListOf<Int>()

		for (i in 0 until n) {
			val num = if (a[i].x2) 8 else {
				0
			} + if (a[i].x1) 4 else {
				0
			} + if (a[i].y2) 2 else {
				0
			} + if (a[i].y1) 1 else {
				0
			}
			aNum.add(num)
		}
		for (i in 0 until n) {
			val t = mutableListOf<Int>()
			for (j in 0 until m) {
				if (aNum[i] == j) {
					t.add(1)
				} else {
					t.add(0)
				}
			}
			result.add(i, t)
		}
		return result
	}

	fun xorTable(a: List<Func2>, b: List<Func2>, n: Int, m: Int): List<Func2> {
		var xor: Boolean
		var xor2: Boolean
		val res = mutableListOf<Func2>()

		for (i in 0 until n) {
			for (j in 0 until m) {
				if (a[i].x2 == b[j].x2 && a[i].x1 == b[j].x1) {
					xor = a[i].y2 xor b[j].y2
					xor2 = a[i].y1 xor b[j].y1
					res.add(Func2(a[i].x2, b[j].x1, xor, xor2))
				}
			}
		}

		return res
	}

	fun fillDef(n: Int) {
		for (i in 0 until n) {
			default.add(
				Func2(
					i / 8 % 2 != 0,
					i / 4 % 2 != 0,
					i / 2 % 2 != 0,
					i % 2 != 0
				)
			)
		}
	}

	fun printFunc(a: List<Func2>): String {
		var result = ""
		for (i in a) {
			result += if (i.x2) "1 " else {
				"0 "
			} +
				if (i.x1) "1 " else {
					"0 "
				} +
				if (i.y2) "1 " else {
					"0 "
				} +
				if (i.y1) "1 " else {
					"0 "
				} +
				"\n"
		}
		return result
	}
}

data class Func2(
	val x2: Boolean,
	val x1: Boolean,
	val y2: Boolean,
	val y1: Boolean,
)