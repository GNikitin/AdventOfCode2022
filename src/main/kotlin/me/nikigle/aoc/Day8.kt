package me.nikigle.aoc

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Thread.currentThread
import java.util.stream.Collectors
import kotlin.math.max
import kotlin.streams.toList

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(currentThread().contextClassLoader.getResourceAsStream("tree-map.txt")))
    var line: String? = reader.readLine()
    if (line == null) {
        println(0)
        return
    }
    val grid = ArrayList<List<Int>>()
    var visibleTrees = 0
    do {
        line!!
        grid.add(line.chars().toList().stream().map {it.toChar().toString().toInt()}.collect(Collectors.toList()))
        line = reader.readLine()
    } while (line != null)

    val visibleTreesMap = Array<Array<Boolean>>(grid.size) {
        when {
            it == 0 || it == grid.size - 1 -> Array(grid[it].size){true}
            else -> Array(grid[it].size){ i -> i == 0 || i == grid[it].size - 1 }
        }
    }

    for (i in 1..grid.size - 2) {
        var max = grid[i][0]
        for (j in 1..grid[i].size - 2) {
            if (max < grid[i][j]) {
                visibleTreesMap[i][j] = true
                max = grid[i][j]
            }
        }
        max = grid[i][grid[i].size - 1]
        for (j in grid[i].size - 2 downTo 1) {
            if (max < grid[i][j]) {
                visibleTreesMap[i][j] = true
                max = grid[i][j]
            }
        }
    }
    for (j in 1..grid[0].size - 2) {
        var max = grid[0][j]
        for (i in 1..grid.size - 2) {
            if (max < grid[i][j]) {
                visibleTreesMap[i][j] = true
                max = grid[i][j]
            }
        }
        max = grid[grid.size - 1][j]
        for (i in grid[j].size - 2 downTo 1) {
            if (max < grid[i][j]) {
                visibleTreesMap[i][j] = true
                max = grid[i][j]
            }
        }
    }

    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (visibleTreesMap[i][j]) visibleTrees++
        }
    }
    println(visibleTrees)
}
