package me.nikigle.aoc

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("signal.txt")))
    val line = reader.readLine()!!

    var pointer = 3
    val buffer = CharArray(4) { i -> line.get(i)}
    while (buffer.toSet().size != 4 && pointer != line.length - 1) {
        pointer++
        buffer[pointer % 4] = line.get(pointer)
    }
    println(pointer + 1)
}