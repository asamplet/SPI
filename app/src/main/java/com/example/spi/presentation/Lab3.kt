package com.example.spi.presentation

class Lab3 {

	val default = mutableListOf<Func>()
	val table = listOf(
		Func(x2 = false, x1 = false, y = true),
		Func(x2 = false, x1 = true, y = true),
		Func(x2 = true, x1 = false, y = false),
		Func(x2 = true, x1 = true, y = true)
	)
	var resTable = emptyList<Func>()
	val n = 8
	val m = 4

	fun calculate(): String {
		var result = ""
		fillDef(n)
		resTable = xorTable(default, table, n, m)
		val result1 = jumpT(resTable, n, n)
		result += "default \n" + printFunc(default) + "\n"
		result += "table \n" + printFunc(table) + "\n"
		result += "result \n" + printFunc(resTable) + "\n"
		for (i in 0 until n) {
			for (j in 0 until n) {
				result += result1[i][j].toString() + " "
			}
			result += "\n"
		}
		result += "\nunitar - " + checkUn(result1, n, n) + "\n"
		return result
	}


	fun checkUn(a: List<List<Int>>, n: Int, m: Int): Boolean{
		var result = true
		val transp = transpose(a, n, m)
		val dgemm = dgemm(a, transp, n, m)

		for(i in 0 until n){
			for(j in 0 until m){
				if(i != j){
					if(dgemm[i][j] != 0) result = false
				} else {
					if (dgemm[i][j] != 1) result = false
				}
			}
		}

		return result
	}

	fun transpose(a: List<List<Int>>, n: Int, m: Int): List<List<Int>> {
		val result = mutableListOf<List<Int>>()
		var t: Int

		for(i in 0 until n){
			val r1 = mutableListOf<Int>()
			for(j in 0 until m){
				r1.add(a[j][i])
			}
			result.add(r1)
		}

		return result
	}

	fun dgemm(a: List<List<Int>>, b: List<List<Int>>, n: Int, m: Int): List<List<Int>> {
		val result = mutableListOf<List<Int>>()

		for(i in 0 until n){
			val r1 = mutableListOf<Int>()
			for(j in 0 until n){
				r1.add(0)
				for(k in 0 until m){
					r1[j] += a[i][k] * b[k][j]
				}
			}
			result.add(r1)
		}

		return result
	}

	fun jumpT(a: List<Func>, n: Int, m: Int): List<List<Int>> {
		val result = mutableListOf<List<Int>>()
		val aNum = mutableListOf<Int>()

		for (i in 0 until n) {
			val num = if (a[i].x2) 4 else {
				0
			} + if (a[i].x1) 2 else {
				0
			} + if (a[i].y) 1 else {
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

	fun xorTable(a: List<Func>, b: List<Func>, n: Int, m: Int): List<Func> {
		var xor: Boolean
		val res = mutableListOf<Func>()

		for (i in 0 until n) {
			for (j in 0 until m) {
				if (a[i].x2 == b[j].x2 && a[i].x1 == b[j].x1) {
					xor = a[i].y xor b[j].y
					res.add(Func(a[i].x2, b[j].x1, xor))
				}
			}
		}

		return res
	}

	fun fillDef(n: Int) {
		for (i in 0 until n) {
			default.add(
				Func(
					i / 4 % 2 != 0,
					i / 2 % 2 != 0,
					i % 2 != 0
				)
			)
		}

	}

	fun printFunc(a: List<Func>): String {
		var result = ""
		for (i in a) {
			result += if (i.x2) "1 " else {
				"0 "
			} +
				if (i.x1) "1 " else {
					"0 "
				} +
				if (i.y) "1 " else {
					"0 "
				} +
				"\n"
		}
		return result
	}
}

data class Func(
	val x2: Boolean,
	val x1: Boolean,
	val y: Boolean,
)