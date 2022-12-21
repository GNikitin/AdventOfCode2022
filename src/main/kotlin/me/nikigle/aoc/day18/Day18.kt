package me.nikigle.aoc.day18

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("lava.txt")))
    var line = reader.readLine()
    val cubes = HashSet<Cube>()
    do {
        val (x, y, z) = line.split(",")
        cubes.add(Cube(x.toInt(), y.toInt(), z.toInt()))

        line = reader.readLine()
    } while (line != null)


    println(totalFaces(cubes))
}