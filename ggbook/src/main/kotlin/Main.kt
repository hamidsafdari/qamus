package io.github.hamidsafdari

import org.jsoup.Jsoup
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.DriverManager

fun main() {
    Class.forName("org.sqlite.JDBC")
    val connection = DriverManager.getConnection("jdbc:sqlite:ggbook.db")
    val stmt = connection.createStatement()
    stmt.executeUpdate(
        """
            CREATE TABLE if NOT EXISTS definition (
              id INTEGER PRIMARY KEY autoincrement,
              entry CHAR(100) NOT NULL,
              definition text NOT NULL
            )
        """.trimIndent()
    )

    val defRegex = ".*?:؛ ج \\d*?، ص: \\d*?".toRegex()

    val resourcePath = "src/main/resources/"
    val wholeText = Jsoup.parse(Files.readString(Paths.get(resourcePath + "ghamus-ghoran-ghaemieh.htm"))).wholeText()

    val output = FileOutputStream(resourcePath + "ghamus-ghoran-ghaemieh.txt", true).bufferedWriter()

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
                val entry = definition.substring(0, colIdx)
                val defText = definition.substring(colIdx + 1).trim()
                stmt.executeUpdate("INSERT INTO definition (id, entry, definition) VALUES ( NULL, \"$entry\", \"$defText\" )")
            }

            definitionLines.clear()
            continue
        }
        definitionLines.add(line.trim())

        stmt.close()
    }
}