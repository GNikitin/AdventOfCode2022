package me.nikigle.aoc.day12

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("elevations.txt")))
    var line = reader.readLine()
    val grid = ArrayList<CharArray>()
    var start: Pair<Int, Int>? = null
    var end: Pair<Int, Int>? = null
    while (line != null) {
        if (line.contains("S")) {
            start = Pair(grid.size, line.indexOf("S"))
            line = line.replace("S", "a")
        }
        if (line.contains("E")) {
            end = Pair(grid.size, line.indexOf("E"))
            line = line.replace("E", "z")
        }
        grid += line.toCharArray()
        line = reader.readLine()
    }
    start!!
    val stepsFromStart = ArrayList<IntArray>()
    grid.forEach { arr ->
        stepsFromStart += IntArray(arr.size) { grid.size * arr.size }
    }
    stepsFromStart[start.first][start.second] = 0

    val q = LinkedList<Pair<Int, Int>>()
    q.add(start)

    while (q.isNotEmpty()) {
        val pos = q.removeFirst()
        val steps = stepsFromStart[pos.first][pos.second]
        val cur = grid[pos.first][pos.second]
        val check = LinkedList<Pair<Int, Int>>()
        check.apply {
            add(getUpOrCurrentPos(pos))
            add(getDownOrCurrentPos(pos, grid.size))
            add(getLeftOrCurrentPos(pos))
            add(getRightOrCurrentPos(pos, grid[0].size))
        }.forEach {
            it.takeIf { grid[it.first][it.second] - cur <= 1  }
                ?.takeIf { stepsFromStart[it.first][it.second] > steps + 1}
                ?.apply {
                    stepsFromStart[it.first][it.second] = steps + 1
                    q.addLast(it)
                }
        }
    }
    println(stepsFromStart[end!!.first][end.second])
}

fun getUpOrCurrentPos(pos: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(max(0, pos.first - 1), pos.second)
}

fun getLeftOrCurrentPos(pos: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(pos.first, max( 0, pos.second - 1))
}

fun getDownOrCurrentPos(pos: Pair<Int, Int>, size: Int): Pair<Int, Int> {
    return Pair(min(size - 1 , pos.first + 1), pos.second)
}

fun getRightOrCurrentPos(pos: Pair<Int, Int>, size: Int): Pair<Int, Int> {
    return Pair(pos.first, min(pos.second + 1, size - 1))
}

