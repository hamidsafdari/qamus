package io.github.hamidsafdari

import org.jsoup.Jsoup
import org.sqlite.SQLiteException
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.DriverManager

fun main() {
    val defRegex = ".*?:؛ ج \\d*?، ص: \\d*?".toRegex()

    val resourcePath = "src/main/resources/"
    val outputPath = "../app/src/main/assets"
    val wholeText = Jsoup.parse(Files.readString(Paths.get(resourcePath + "ghamus-ghoran-ghaemieh.htm"))).wholeText()


    Class.forName("org.sqlite.JDBC")
    val connection = DriverManager.getConnection("jdbc:sqlite:$outputPath/book.db")
    val stmt = connection.createStatement()
    stmt.executeUpdate(
        """
            CREATE TABLE if NOT EXISTS Entry (
              id INTEGER NOT NULL PRIMARY KEY autoincrement,
              keyword TEXT,
              definition text
            )
        """.trimIndent()
    )

    val lines = wholeText.split("\n")
    var started = false
    val definitionLines = mutableListOf<String>()

    for (line in lines) {
        if (!started) {
            if (line.startsWith("[جلد اول]")) started = true
            continue
        }

        if (line.startsWith("[خاتمه]")) break
        if (line.startsWith("قاموس قرآن، ج")) continue
        if (line.isBlank()) continue

        // definition start
        if (line.trim().matches(defRegex)) {
            val definition = definitionLines.joinToString("")
                .removePrefix("اشاره")
                .trim()
            if (definition.isNotBlank()) {
                val colIdx = definition.indexOf(":")
                val keyword = definition.substring(0, colIdx)
                val defText = definition.substring(colIdx + 1).trim()
                try {
                    stmt.executeUpdate("INSERT INTO Entry (id, keyword, definition) VALUES ( NULL, \"$keyword\", \"$defText\" )")
                } catch (e: SQLiteException) {
                    println("exception when saving: $keyword, $definition")
                    println(e.message)
                }
            }

            definitionLines.clear()
            continue
        }
        definitionLines.add(line.trim())

        stmt.close()
    }
}