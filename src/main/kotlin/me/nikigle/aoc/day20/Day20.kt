package me.nikigle.aoc.day20

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("encrypted.txt")))
    var line = reader.readLine()
    val list = LinkedList<MovingElement>()
    do {
        list.add(MovingElement(line!!.toInt()))
        line = reader.readLine()
    } while (line != null)

    var elementsToMove = list.size
    var index = 0
    while (elementsToMove != 0) {
        if (list[index].moved) {
            index = (index + 1) % list.size
            continue
        }
        val elt = list.removeAt(index)

        var newIndex: Int
        val offset = index + elt.value
        if (offset >= 0) {
            newIndex = offset % list.size
        } else {
            newIndex = (list.size + (offset % list.size)) % list.size
        }

        if (newIndex == index) {
            index = (index + 1) % list.size
        }
        list.add(newIndex, elt)
        elt.moved = true
        elementsToMove--
    }

    val indexOf0 = list.indexOf(MovingElement(0, true))
    println(list[(indexOf0 + 1000) % list.size].value + list[(indexOf0 + 2000) % list.size].value + list[(indexOf0 + 3000) % list.size].value)
}