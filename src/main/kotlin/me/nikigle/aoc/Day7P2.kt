package me.nikigle.aoc

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    var rootDir = executeCommands()
    fillDirSizesRecursively(rootDir)
    print(flattenAllDirs(rootDir).stream()
        .filter{ l -> l.size >= rootDir.size - 40000000}
        .sorted { f1, f2 -> if (f1.size < f2.size) -1 else 1 }
        .findFirst().map { f -> f.size }.orElse(0))
}

private fun flattenAllDirs(rootDir: ElfFile): List<ElfFile> {
    val result = LinkedList<ElfFile>()
    result.add(rootDir)
    for (testFile in rootDir.files.values) {
        if (testFile.files.size != 0) {
            result.addAll(flattenAllDirs(testFile))
        }
    }
    return result
}

private fun executeCommands(): ElfFile {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("commands.txt")))
    var line: String? = reader.readLine()

    val rootFile = ElfFile(null, "/")
    var curFile = rootFile

    do {
        line!!
        if (line.startsWith("$ cd")) {
            curFile = executeCd(line.substring(5), curFile)
            line = reader.readLine()
        } else if (line.startsWith("$ ls")) {
            line = reader.readLine()
            curFile.files.clear()
            while (line != null && !line.startsWith("$")) {
                addFileInfo(curFile, line)
                line = reader.readLine()
            }
        } else {
            reader.readLine()
        }
    } while (line != null)
    return rootFile
}

private fun addFileInfo(curFile: ElfFile, line: String) {
    val fileInfo = line.split(" ")
    val file = ElfFile(curFile, fileInfo[1])
    if (fileInfo[0] != "dir") {
        file.size = fileInfo[0].toInt()
    }
    curFile.files[fileInfo[1]] = file
}

private fun executeCd(fileName: String, curFile: ElfFile): ElfFile =
     when (fileName) {
        "/" -> {
            var file = curFile
            while (file.parentFile != null) {
                file = file.parentFile!!
            }
            file
        }

        ".." -> curFile.parentFile ?: curFile

        else ->
            if (curFile.files.containsKey(fileName)) {
                 curFile.files[fileName]!!
            } else {
                curFile
            }
    }

private fun fillDirSizesRecursively(elfFile: ElfFile) {
    var filesSize = 0
    for(testFile in elfFile.files.values) {
        if (testFile.files.isNotEmpty()) {
            fillDirSizesRecursively(testFile)
        }
        filesSize += testFile.size
    }
    elfFile.size = filesSize
}