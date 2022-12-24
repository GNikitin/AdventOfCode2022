package me.nikigle.aoc.day23

data class Coordinate(val y: Int, val x: Int)

fun checkNorth(elves: HashSet<Coordinate>, c: Coordinate): Coordinate? {
    val northCoord = Coordinate(c.y - 1, c.x)
    return if (elves.contains(Coordinate(northCoord.y, northCoord.x - 1))
        || elves.contains(northCoord)
        || elves.contains(Coordinate(northCoord.y, northCoord.x + 1))
    ) null else northCoord
}

fun checkSouth(elves: HashSet<Coordinate>, c: Coordinate): Coordinate? {
    val southCoord = Coordinate(c.y + 1, c.x)
    return if (elves.contains(Coordinate(southCoord.y, southCoord.x - 1))
        || elves.contains(southCoord)
        || elves.contains(Coordinate(southCoord.y, southCoord.x + 1))
    ) null else southCoord
}

fun checkWest(elves: HashSet<Coordinate>, c: Coordinate): Coordinate? {
    val westCoord = Coordinate(c.y, c.x - 1)
    return if (elves.contains(Coordinate(westCoord.y - 1, westCoord.x))
        || elves.contains(westCoord)
        || elves.contains(Coordinate(westCoord.y + 1, westCoord.x))
    ) null else westCoord
}

fun checkEast(elves: HashSet<Coordinate>, c: Coordinate): Coordinate? {
    val eastCoord = Coordinate(c.y, c.x + 1)
    return if (elves.contains(Coordinate(eastCoord.y - 1, eastCoord.x))
        || elves.contains(eastCoord)
        || elves.contains(Coordinate(eastCoord.y + 1, eastCoord.x))
    ) null else eastCoord
}