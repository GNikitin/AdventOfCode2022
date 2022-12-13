package me.nikigle.aoc.day13

import java.lang.Integer.min
import kotlin.collections.*

class Packet(
    val value: Int? = null,
    val packets: List<Packet> = listOf()
) : Comparable<Packet> {

    fun shallowCopy(): Packet {
        return Packet(value, packets.map { it.shallowCopy() })
    }

    override fun compareTo(other: Packet): Int {
        if (value != null) {
            return if (other.value != null) value - other.value
            else Packet(packets = listOf(Packet(value))).compareTo(other)
        }

        val modifiedOther = if (other.value == null) other else Packet(packets = listOf(Packet(other.value)))

        for (i in 0 until min(packets.size, modifiedOther.packets.size)) {
            val c = packets[i].compareTo(modifiedOther.packets[i])
            if (c != 0) return c
        }

        return packets.size - modifiedOther.packets.size
    }

    override fun equals(other: Any?): Boolean {
        val otherPacket = other as Packet
        if (value != null || otherPacket.value != null) return value == otherPacket.value
        return packets == otherPacket.packets
    }

}