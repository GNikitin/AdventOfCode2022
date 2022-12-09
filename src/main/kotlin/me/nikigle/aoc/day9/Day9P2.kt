package me.nikigle.aoc.day9

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Thread.currentThread
import java.util.*

fun main(args: Array<String>) {
    val reader =
        BufferedReader(InputStreamReader(currentThread().contextClassLoader.getResourceAsStream("rope-directions.txt")))
    var line: String? = reader.readLine()
    if (line == null) {
        println(0)
        return
    }
    var tailCommandsAcc: MutableList<String> = LinkedList()
    val ts = TailState()
    do {
        line!!
        tailCommandsAcc = LinkedList(executeCommands(listOf(line), ts = ts, tailCommands = tailCommandsAcc))
        line = reader.readLine()
    } while (line != null)
    var tailCommands: List<String> = tailCommandsAcc
    repeat(7) {
        tailCommands = executeCommands(tailCommands)
    }

    val visited = mutableSetOf("0/0")

    executeCommands(tailCommands, visited = visited)
    println(visited.size)
}



