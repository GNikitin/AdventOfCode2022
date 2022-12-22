package me.nikigle.aoc.day20

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("encrypted.txt")))
    var line = reader.readLine()
    val originalList = ArrayList<MovingLongElement>()
    var id = 0
    do {
        originalList.add(MovingLongElement(line!!.toLong() * 811589153, id++))
        line = reader.readLine()
    } while (line != null)

    var elementsToMove = originalList.size
    val list = ArrayList(originalList)
    repeat(10) {
        for (originalIndex in 0 until originalList.size) {
            val index = list.indexOf(originalList[originalIndex])
            val elt = list.removeAt(index)

            var newIndex: Int
            val offset = index + elt.value
            if (offset >= 0) {
                newIndex = (offset % list.size).toInt()
            } else {
                newIndex = ((list.size + (offset % list.size)) % list.size).toInt()
            }

            list.add(newIndex, elt)
            elementsToMove--
        }
    }

    val indexOf0 = list.indexOfFirst { it.value == 0L }
    println(list[(indexOf0 + 1000) % list.size].value + list[(indexOf0 + 2000) % list.size].value + list[(indexOf0 + 3000) % list.size].value)
}