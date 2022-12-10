package me.nikigle.aoc.day10

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("asm.txt")))
    var line: String? = reader.readLine()

    var cycle = 0
    var x = 1
    var crt = Array(40 * 6) { '.' }.toMutableList()
    var offset = 1
    do {
        line!!
        if (line == "noop") {
            cycle += 1
            if (cycle % 40 == 0) offset = cycle + 1
            if (cycle - offset == (x - 1) || cycle - offset == x || cycle - offset == (x + 1)) {
                crt[cycle - 1] = '#'
            }
        } else {
            val (_, add) = line.split(" ")
            cycle += 1
            if (cycle % 40 == 0) offset = cycle + 1
            if (cycle - offset == (x - 1) || cycle - offset == x || cycle - offset == (x + 1)) {
                crt[cycle - 1] = '#'
            }
            cycle += 1
            if (cycle % 40 == 0) offset = cycle + 1
            if (cycle - offset  == (x - 1) || cycle - offset == x || cycle - offset == (x + 1)) {
                crt[cycle - 1] = '#'
            }
            x += add.toInt()
        }

        line = reader.readLine()
    } while (line != null)
    repeat(6) {
        println(crt.subList(it * 40, 40 * (it + 1) )
            .joinToString("", "", "", -1, "") { "" + it })
    }
}