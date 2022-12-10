package me.nikigle.aoc.day10

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("asm.txt")))
    var line: String? = reader.readLine()

    var cyclesToCheck = LinkedList<Int>()
    cyclesToCheck.addAll(listOf(20, 60, 100, 140, 180, 220))
    var cycle = 0
    var x = 1
    var sum = 0
    do {
        line!!
        if (line == "noop") {
            cycle += 1
            if (cycle == cyclesToCheck.peekFirst()) {
                sum += cyclesToCheck.removeFirst() * x
            }
        } else {
            val (_, add) = line.split(" ")
            cycle += 1
            if (cycle == cyclesToCheck.peekFirst()) {
                sum += cyclesToCheck.removeFirst() * x
            }
            cycle += 1
            if (cycle == cyclesToCheck.peekFirst()) {
                sum += cyclesToCheck.removeFirst() * x
            }
            x += add.toInt()
        }

        line = reader.readLine()
    } while (line != null)
    println(sum)
}