package me.nikigle.aoc.day18

data class Cube(
    val x: Int,
    val y: Int,
    val z: Int
)

fun totalFaces(cubes: Set<Cube>): Int {
    var totalSides = 0
    cubes.forEach {
        totalSides += neighbourCubes(it).count { cube -> !cubes.contains(cube) }
    }
    return totalSides
}

fun neighbourCubes(it: Cube): List<Cube> {
    return listOf(
        Cube(it.x - 1, it.y, it.z),
        Cube(it.x + 1, it.y, it.z),
        Cube(it.x, it.y - 1, it.z),
        Cube(it.x, it.y + 1, it.z),
        Cube(it.x, it.y, it.z - 1),
        Cube(it.x, it.y, it.z + 1),
    )
}