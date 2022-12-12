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
    var end: Pair<Int, Int>? = null
    val starts = ArrayList<Pair<Int, Int>>()
    while (line != null) {
        if (line.contains("S")) {
            line = line.replace("S", "a")
        }
        if (line.contains("E")) {
            end = Pair(grid.size, line.indexOf("E"))
            line = line.replace("E", "z")
        }
        line.forEachIndexed { i, c ->
            if (c == 'a') {
                starts.add(Pair(grid.size, i))
            }

        }
        grid += line.toCharArray()
        line = reader.readLine()
    }
    var minSteps = grid.size * grid[0].size
    starts.forEach { start ->
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
                it.takeIf { grid[it.first][it.second] - cur <= 1 }
                    ?.takeIf { stepsFromStart[it.first][it.second] > steps + 1 }
                    ?.apply {
                        stepsFromStart[it.first][it.second] = steps + 1
                        q.addLast(it)
                    }
            }
        }
        minSteps = min(stepsFromStart[end!!.first][end.second], minSteps)
    }
    println(minSteps)
}

