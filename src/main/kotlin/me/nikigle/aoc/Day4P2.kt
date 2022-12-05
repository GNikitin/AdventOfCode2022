package me.nikigle.aoc;

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("section-assignments.txt")))
    var line = reader.readLine()

    var overlapCounter = 0
    do {
        line!!
        val elfRanges = line.split(',')
        val first: Pair<Int, Int> = Pair(elfRanges[0].split('-')[0].toInt(), elfRanges[0].split('-')[1].toInt())
        val second: Pair<Int, Int> = Pair(elfRanges[1].split('-')[0].toInt(), elfRanges[1].split('-')[1].toInt())
        if (!(first.second < second.first || second.second < first.first)) {
                overlapCounter++
        }
        line = reader.readLine()
    } while (line != null)

    println(overlapCounter)
}