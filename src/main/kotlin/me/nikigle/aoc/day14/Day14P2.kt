package me.nikigle.aoc.day14

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("rocks.txt")))
    var line = reader.readLine()

    val rocksPathes = ArrayList<MutableList<Pair<Int, Int>>>()
    var maxY = 0
    var maxX = 0
    var minX = 500
    do {
        rocksPathes.add(LinkedList<Pair<Int, Int>>().apply {
            addAll(line!!.split(" -> ").map { coord ->

                val (x, y) = coord.split(",").map { it.toInt() }
                maxX = max(x, maxX)
                minX = min(x, minX)
                maxY = max(y, maxY)
                Pair(x, y)
            })
        })
        line = reader.readLine()
    } while (line != null)

    val cave = Array(maxY + 3) {
        if (it == maxY + 2) Array(maxX + 500) { '#' }
        else Array(maxX + 500) { '.' }
    }

    rocksPathes.forEach {
        var prev = it.removeFirst()
        while (it.isNotEmpty()) {
            val cur = it.removeFirst()
            if (prev.first == cur.first) {
                for (y in min(prev.second, cur.second)..max(prev.second, cur.second)) {
                    cave[y][cur.first] = '#'
                }
            } else {
                for (x in min(prev.first, cur.first)..max(prev.first, cur.first)) {
                    cave[cur.second][x] = '#'
                }
            }
            prev = cur
        }
    }

    printCave(cave, minX - 10)

    var counter = 0
    while (cave[0][500] == '.') {
        var falling = true
        var curX = 500
        var curY = 0
        while (falling) {
            if (cave[curY + 1][curX] == '.') {
                curY++
            } else {
                if (cave[curY + 1][curX - 1] == '.') {
                    curY++
                    curX--
                } else if (cave[curY + 1][curX + 1] == '.') {
                    curY++
                    curX++
                } else {
                    cave[curY][curX] = 'o'
                    falling = false
                    counter++
                }
            }
        }
    }
    println(counter)
}

private fun printCave(cave: Array<Array<Char>>, xCut: Int) {
    cave.forEach {
        it.filterIndexed { i, c -> i > xCut }.forEach { print(it) }
        println()
    }
}