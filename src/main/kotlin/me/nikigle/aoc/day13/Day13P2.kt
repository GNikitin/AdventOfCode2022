package me.nikigle.aoc.day13

import kotlinx.serialization.json.*
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("packets.txt")))
    var line = reader.readLine()
    var packets = ArrayList<Packet>()

    do {
        if (line.isNotEmpty()) {
            packets.add(parsePacket(line!!))
        }
        line = reader.readLine()
    } while (line != null)

    val distractionPackets = listOf(Packet(packets = listOf(Packet(packets = listOf(Packet(2))))),
        Packet(packets = listOf(Packet(packets = listOf(Packet(6)))))
        )
    packets.addAll(distractionPackets)
    var decoderKey = 1
    packets.sorted().forEachIndexed { i,p ->
        if (p == distractionPackets[0] || p == distractionPackets[1]) {

            decoderKey *= (i + 1)
        }
    }
    println(decoderKey)
}

