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
    var counter = 1
    var group = ArrayList<String>(3)
    do {
        line!!
        group.add(line)
        if (counter == 3) {
            counter = 0
            priority += findPriority(group[0], group[1], group[2])
            group.clear()
        }
        line = reader.readLine()
        counter++
    } while (line != null)

    println(priority)
}

fun findPriority(rucksack: String, secondRucksack: String, thirdRucksack: String): Int {
    val firstSet = rucksack.toSet()
    val secondSet = secondRucksack.toSet()
    for (item in thirdRucksack.toCharArray()) {
        if (firstSet.contains(item) && secondSet.contains(item)) {
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
