package me.nikigle.aoc

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("strategy-rps.txt")))
    var line: String? = reader.readLine()
    if (line == null) {
        println(0)
        return
    }
    var score = 0
    do {
        line!!
        when (line) {
            "A X" -> score += 4
            "A Y" -> score += 8
            "A Z" -> score += 3
            "B X" -> score += 1
            "B Y" -> score += 5
            "B Z" -> score += 9
            "C X" -> score += 7
            "C Y" -> score += 2
            "C Z" -> score += 6
        }
        line = reader.readLine()
    } while (line != null)
    println(score)
}