package me.nikigle.aoc

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Thread.currentThread
import kotlin.math.max

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(currentThread().contextClassLoader.getResourceAsStream("elf-calories.txt")))
    var line: String? = reader.readLine()
    if (line == null) {
        println(0)
        return
    }
    var maxCalories = 0
    var curElfCalories = 0
    val score = 0
    do {
        line!!
        if (line.isEmpty()) {
            maxCalories = max(curElfCalories, maxCalories)
            curElfCalories = 0
        } else {
            curElfCalories += line.toInt()
        }
        line = reader.readLine()
    } while (line != null)
    println(maxCalories)
}
