package me.nikigle.aoc.day9

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Thread.currentThread
import java.util.*
import java.util.Optional.ofNullable

fun main(args: Array<String>) {
    val reader =
        BufferedReader(InputStreamReader(currentThread().contextClassLoader.getResourceAsStream("rope-directions.txt")))
    var line: String? = reader.readLine()
    if (line == null) {
        println(0)
        return
    }
    val visited = mutableSetOf("0/0")
    val ts = TailState()
    do {
        line!!
        executeCommands(listOf(line), ts, visited = visited)
        line = reader.readLine()
    } while (line != null)

    println(visited.size)
}

fun executeCommands(
    commands: List<String>,
    ts: TailState = TailState(),
    tailCommands: MutableList<String> = LinkedList<String>(),
    visited: MutableSet<String>? = null) : List<String> {

    commands.forEach {
        val (direction, times) = it.split(" ")
        val move = when(direction) {
            "D" -> ::moveDown
            "U" -> ::moveUp
            "L" -> ::moveLeft
            "R" -> ::moveRight
            "LU" -> ::moveLeftUp
            "RU" -> ::moveRightUp
            "RD" -> ::moveRightDown
            else -> ::moveLeftDown
        }
        repeat(times.toInt()){
            ofNullable(move(ts)).ifPresent(tailCommands::add)
            visited?.add("" + ts.curTailX + "/" + ts.curTailY)
        }
    }
    return tailCommands
}
class TailState(
    var curTailX: Int = 0,
    var curTailY: Int = 0,
    var relativePos: Int = 0
)
