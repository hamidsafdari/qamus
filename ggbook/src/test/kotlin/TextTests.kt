import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class TextTests {
    @Test
    fun testFindDefinitionStart() {
        val start = "ابّ:؛ ج 1، ص: 1"
        val regex = ".*?:؛ ج \\d*?، ص: \\d*?".toRegex()
        assertTrue(start.matches(regex))
    }
}