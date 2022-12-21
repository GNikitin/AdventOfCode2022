package me.nikigle.aoc.day18

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("lava.txt")))
    var line = reader.readLine()
    val cubes = HashSet<Cube>()
    var minX = 0
    var minY = 0
    var minZ = 0
    var maxX = 0
    var maxY = 0
    var maxZ = 0
    do {
        val (x, y, z) = line.split(",")
        cubes.add(Cube(x.toInt(), y.toInt(), z.toInt()))
        minX = min(minX, x.toInt())
        minY = min(minY, y.toInt())
        minZ = min(minZ, z.toInt())
        maxZ = max(maxZ, z.toInt())
        maxY = max(maxY, y.toInt())
        maxX = max(maxX, x.toInt())
        line = reader.readLine()
    } while (line != null)

    val allCubes = HashSet<Cube>()
    for (x in minX - 1..maxX + 1) {
        for (y in minY - 1..maxY + 1) {
            for (z in minZ - 1..maxZ + 1) {
                allCubes.add(Cube(x, y, z))
            }
        }
    }

    allCubes -= cubes
    val q = LinkedList<Cube>()
    q.add(Cube(minX - 1, minY - 1, minZ - 1))
    allCubes -= q.first
    while (q.isNotEmpty()) {
        val cube = q.removeFirst()
        neighbourCubes(cube).forEach {
            if (allCubes.remove(it)) {
                q.add(it)
            }
        }
    }

    println(totalFaces(cubes) - totalFaces(allCubes))
}