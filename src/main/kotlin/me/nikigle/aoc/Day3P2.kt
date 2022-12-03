import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main(args: Array<String>) {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("rucksack-priorities.txt")))
    var line: String? = reader.readLine()

    if (line == null) {
        println(0)
        return
    }
    var priority = 0
    do {
        line!!
        priority += findPriority(line)
        line = reader.readLine()
    } while (line != null)
    println(priority)
}

fun findPriority(line: String): Int {
    if(line.length % 2 != 0) println("aa")
    var secondCompartmentItems = line.subSequence(line.length/2, line.length).toSet()
    for (item in line.subSequence(0 until line.length/2)) {
        if (secondCompartmentItems.contains(item)) {
            return calculatePriority(item)
        }
    }
    return 0

}

private fun calculatePriority(item: Char): Int {
    if (item.isLowerCase()) {
        return item.code - 96
    } else {
        return item.code - 38
    }
}
