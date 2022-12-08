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
    do {
        line!!
        grid.add(line.chars().toList().stream().map {it.toChar().toString().toInt()}.collect(Collectors.toList()))
        line = reader.readLine()
    } while (line != null)

    var score = 0
    for (i in 1..grid.size - 2) {
        for (j in 1..grid[0].size - 2){
            var leftScore = 1
            for ( k in j - 1 downTo 1) {
                if (grid[i][k] < grid[i][j]) leftScore++ else break
            }
            var rightScore = 1
            for ( k in j + 1 until grid[i].size - 1) {
                if (grid[i][k] < grid[i][j]) rightScore++ else break
            }
            var topScore = 1
            for (k in i - 1 downTo 1) {
                if (grid[k][j] < grid[i][j]) topScore++ else break
            }
            var botScore = 1
            for (k in i + 1 until grid.size - 1) {
                if (grid[k][j] < grid[i][j]) botScore++ else break
            }
            score = max(score, botScore*topScore*leftScore*rightScore)
        }
    }
    println(score)
}
