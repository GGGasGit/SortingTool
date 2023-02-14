package sorting
import java.util.Scanner
import java.io.File

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    try {

        val dataType = if ("-dataType" in args) {
            if ("long" in args || "line" in args || "word" in args) {
                args[args.indexOf("-dataType") + 1]
            } else {
                throw Exception("No data type defined!")
            }
        } else {
            "word"
        }

        val sortingType = if ("-sortingType" in args) {
            if ("natural" in args || "byCount" in args) {
                args[args.indexOf("-sortingType") + 1]
            } else {
                throw Exception("No sorting type defined!")
            }
        } else {
            "natural"
        }

        val parameterPattern = Regex("-[a-zA-Z]+")
        val parameters = args.filter { parameterPattern.matches(it) && it !="-sortingType" && it != "-dataType" }
        if (parameters.isNotEmpty()) {
            for (parameter in parameters) {
                println("\"$parameter\" is not a valid parameter. It will be skipped.")
            }
        }

        val inputFileName = if ("-inputFile" in args) args[args.indexOf("-inputFile") + 1] else ""
        val outputFileName = if ("-outputFile" in args) args[args.indexOf("-outputFile") + 1] else ""

        val separator = File.separator
        val workingDirectory = System.getProperty ("user.dir")
        val inputPath = "${workingDirectory}${separator}$inputFileName"
        val outputPath = "${workingDirectory}${separator}$outputFileName"

        val splitPattern = Regex("\\s+")

        when (dataType) {
            "long" -> {
                val numberPattern = Regex("-?\\d+")
                val numbers = mutableListOf<Int>()

                if (inputFileName == "") {
                    while (scanner.hasNext()) {
                        val nextItem = scanner.next()
                        if (numberPattern.matches(nextItem)) {
                            numbers.add(nextItem.toInt())
                        } else {
                            println("\"$nextItem\" is not a long. It will be skipped.")
                        }
                    }
                } else {
                    val inputData = File(inputPath).readText().split(splitPattern)
                    for (input in inputData) {
                        if (numberPattern.matches(input)) {
                            numbers.add(input.toInt())
                        } else {
                            println("\"$input\" is not a long. It will be skipped.")
                        }
                    }
                }

                val totalNumbers = numbers.size

                when (sortingType) {
                    "natural" -> {
                        val sortedNumbers = numbers.sorted().joinToString(" ")

                        val result = "Total numbers: $totalNumbers.\n" +
                                "Sorted data: $sortedNumbers"

                        if (outputFileName == "") {
                            print(result)
                        } else {
                            File(outputPath).writeText(result)
                        }
                    }

                    "byCount" -> {
                        val numbersByCount = mutableMapOf<Int, Int>()
                        for (number in numbers) {
                            if (numbersByCount.containsKey(number)) {
                                numbersByCount[number] = numbersByCount[number]!! + 1
                            } else {
                                numbersByCount[number] = 1
                            }
                        }

                        val numbersSortedByCount = numbersByCount.toSortedMap().entries.sortedBy { it.value }

                        var result = "Total numbers: $totalNumbers."
                        for ((number, times) in numbersSortedByCount) {
                           result += "\n$number: $times time(s), ${times * 100 / totalNumbers}%"
                        }

                        if (outputFileName == "") {
                            print(result)
                        } else {
                            File(outputPath).writeText(result)
                        }
                    }

                    else -> print("Unknown sorting type provided.")
                }
            }

            "line" -> {
                val lines = mutableListOf<String>()

                if (inputFileName == "") {
                    while (scanner.hasNextLine()) {
                        lines.add(scanner.nextLine())
                    }
                } else {
                    val inputData = File(inputPath).readLines()
                    for (input in inputData) {
                        lines.add(input)
                    }
                }

                val totalLines = lines.size

                when (sortingType) {
                    "natural" -> {
                        val sortedLines = lines.sorted().joinToString("\n")

                        val result = "Total lines: $totalLines\n" +
                                "Sorted data:\n$sortedLines"

                        if (outputFileName == "") {
                            print(result)
                        } else {
                            File(outputPath).writeText(result)
                        }
                    }

                    "byCount" -> {
                        val linesByCount = mutableMapOf<String, Int>()
                        for (line in lines) {
                            if (linesByCount.containsKey(line)) {
                                linesByCount[line] = linesByCount[line]!! + 1
                            } else {
                                linesByCount[line] = 1
                            }
                        }

                        val linesSortedByCount = linesByCount.toSortedMap().entries.sortedBy { it.value }

                        var result = "Total lines: $totalLines."
                        for ((line, times) in linesSortedByCount) {
                            result += "\n$line: $times time(s), ${times * 100 / totalLines}%"
                        }

                        if (outputFileName == "") {
                            print(result)
                        } else {
                            File(outputPath).writeText(result)
                        }
                    }

                    else -> print("Unknown sorting type provided.")
                }

            }

            "word" -> {
                val words = mutableListOf<String>()

                if (inputFileName == "") {
                    while (scanner.hasNext()) {
                        words.add(scanner.next())
                    }
                } else {
                    val inputData = File(inputPath).readText().split(splitPattern)
                    for (input in inputData) {
                        words.add(input)
                    }
                }

                val totalWords = words.size

                when (sortingType) {
                    "natural" -> {
                        val sortedWords = words.sorted().joinToString(" ")

                        val result = "Total words: $totalWords.\n" +
                                "Sorted data: $sortedWords"

                        if (outputFileName == "") {
                            print(result)
                        } else {
                            File(outputPath).writeText(result)
                        }
                    }

                    "byCount" -> {
                        val wordsByCount = mutableMapOf<String, Int>()
                        for (word in words) {
                            if (wordsByCount.containsKey(word)) {
                                wordsByCount[word] = wordsByCount[word]!! + 1
                            } else {
                                wordsByCount[word] = 1
                            }
                        }

                        val wordsSortedByCount = wordsByCount.toSortedMap().entries.sortedBy { it.value }

                        var result = "Total words: $totalWords."
                        for ((word, times) in wordsSortedByCount) {
                            result += "\n$word: $times time(s), ${times * 100 / totalWords}%"
                        }

                        if (outputFileName == "") {
                            print(result)
                        } else {
                            File(outputPath).writeText(result)
                        }
                    }

                    else -> print("Unknown sorting type provided.")
                }
            }

            else -> print("Unknown data type provided.")
        }

    } catch (error: Exception) {
        print(error)
    }
}
