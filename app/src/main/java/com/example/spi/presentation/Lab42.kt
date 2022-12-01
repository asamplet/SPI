package com.example.spi.presentation

class Lab42 {

	val default = mutableListOf<Func>()
	val table = listOf(
		Func(x2 = false, x1 = false, y = false),
		Func(x2 = false, x1 = true, y = false),
		Func(x2 = true, x1 = false, y = false),
		Func(x2 = true, x1 = true, y = false)
	)
	var resTable = emptyList<Func>()
	val n = 8
	val m = 4
	val na = 8
	val ma = 4

	fun calculate(): String {
		val h3 = arrayOf(arrayOf(1, 1), arrayOf(1, -1))
		var unit = arrayOf(arrayOf(1, 0), arrayOf(0, 1))
		var h1 = arrayOf<Array<Int>>()
		for (i in 0 until ma) {
			var array = arrayOf<Int>()
			for (j in 0 until ma) {
				array += when (i) {
					0    -> 1
					1    -> if (j % 2 == 1) -1 else 1
					2    -> if (j < 2) 1 else -1
					3    -> if (j == 0 || j == 3) 1 else -1
					else -> 0
				}
			}
			h1 += array
		}
		var h2 = arrayOf<Array<Int>>()
		for (i in 0 until ma) {
			var array = arrayOf<Int>()
			for (j in 0 until ma) {
				array += if (i % 2 == 0 && j == 0) 1
				else if (i % 2 != 0 && j == 0) -1 else 0
			}
			h2 += array
		}
		val h = tender(h1, h3, 4, 4, 2, 2)

		var m3 = arrayOf<Array<Int>>()
		for (i in 0 until n) {
			var array = arrayOf<Int>()
			for (j in 0 until n) {
				array += when {
					i % 2 != j % 2 -> 0
					j < 2 || i < 2 -> 1
					else           -> -1
				}
			}
			m3 += array
		}
		m3[2][4] = 1
		m3[4][2] = 1
		m3[3][5] = 1
		m3[5][3] = 1
		m3[6][6] = 1
		m3[7][7] = 1

		var result = ""
		result += "HADAM \n" + printArr(h, 8, 8)
		result += "THR MATR \n" + printArr(m3, 8, 8)
		result += "HADAM1 \n" + printArr(h1, m, m)
		result += "HADAM2 \n" + printArr(h2, 4, 1)

		fillDef(na)
		result += " DEF \n" + printFunc(default)
		result += " TAB \n" + printFunc(table)

		resTable = xorTable(default, table, na, ma)

		result += " RES \n" + printFunc(resTable)

		val res1 = jumpT(default, na, na)
		result += " RES1 \n" + printArr(res1, 8, 8)
		val res2 = dgemm(h, res1, 8, 8)
		result += " RES2 \n" + printArr(res2, 8, 8)
		val res3 = dgemm(res2, m3, 8, 8)
		result += " RES3 \n" + printArr(res3, 8, 8)

		if(res3[0][0] == 0 && res3[1][0] == 0){
			result += "\nBALANCE\n"
		}
		if (res3[2][0] == 0 && res3[3][0] == 0){
			result += "\nCONST\n"
		}

		return result
	}

	fun dgemm(a: Array<Array<Int>>, b: Array<Array<Int>>, n: Int, m: Int): Array<Array<Int>> {
		var result = arrayOf<Array<Int>>()

		for (i in 0 until n) {
			var array = arrayOf<Int>()
			for (j in 0 until n) {
				array += 0
				for (k in 0 until m) {
					array[j] += a[i][k] * b[k][j]
				}
			}
			result += array
		}

		return result
	}

	fun printArr(a: Array<Array<Int>>, n: Int, m: Int): String {
		var result = ""
		for (i in 0 until n) {
			for (j in 0 until m) {
				result += if(a[i][j]>=0) " " + a[i][j].toString() + " "
				else a[i][j].toString() + " "
			}
			result += "\n"
		}
		return result
	}

	fun tender(a: Array<Array<Int>>, b: Array<Array<Int>>, na: Int, ma: Int, nb: Int, mb: Int): Array<Array<Int>> {
		var tem = arrayOf<Array<Int>>()
		for (i in 0 until na * nb) {
			var array = arrayOf<Int>()
			for (j in 0 until ma * mb) {
				array += 9
			}
			tem += array
		}
		for (k in 0 until nb) {
			for (l in 0 until mb) {
				for (i in 0 until na) {
					for (j in 0 until ma) {
						tem[i + k * na][j + l * ma] = a[i][j] * b[k][l]
					}
				}
			}
		}
		return tem
	}

	fun jumpT(a: List<Func>, n: Int, m: Int): Array<Array<Int>> {
		var result = arrayOf<Array<Int>>()
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
			var array = arrayOf<Int>()
			for (j in 0 until m) {
				array += if (aNum[i] == j) {
					1
				} else {
					0
				}
			}
			result += array
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
		for (i in 0 until n / 2) {
			for (j in 0 until  2) {
				default.add(
					Func(
						i / 2 % 2 != 0,
						i % 2 != 0,
						j % 2 != 0
					)
				)
			}
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