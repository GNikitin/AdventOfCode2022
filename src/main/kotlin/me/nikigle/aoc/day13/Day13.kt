package me.nikigle.aoc.day13

import kotlinx.serialization.json.*
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("packets.txt")))
    var line = reader.readLine()
    val pairs = ArrayList<Pair<Packet, Packet>>()

    do {
        if (line.isNotEmpty()) {
            pairs.add(Pair(parsePacket(line!!), parsePacket(reader.readLine()!!)))
        }
        line = reader.readLine()
    } while (line != null)

    var count = 0
    pairs.forEachIndexed { i, pair ->
        val test = listOf(pair.first, pair.second)
        val sorted = listOf(pair.first.shallowCopy(), pair.second.shallowCopy()).sorted()
        if (test == sorted) count += (i + 1)
    }
    println(count)
}

fun parsePacket(p: String): Packet {
    if ("[]" == p) return Packet()
    val packets = ArrayList<Packet>()
    Json.parseToJsonElement(p).jsonArray.forEach {
        if (it is JsonArray) packets.add(parsePacket(it.toString()))
        if (it is JsonPrimitive) packets.add(Packet(it.content.toInt()))
    }
    return Packet(packets = packets)
}

