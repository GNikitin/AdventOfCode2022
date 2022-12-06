package me.nikigle.aoc

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("signal.txt")))
    val line = reader.readLine()!!

    var pointer = 13
    val buffer = CharArray(14) { i -> line.get(i)}
    while (buffer.toSet().size != 14 && pointer != line.length - 1) {
        pointer++
        buffer[pointer % 14] = line.get(pointer)
    }
    println(pointer + 1)
}