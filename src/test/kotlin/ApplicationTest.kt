import com.example.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ApplicationTest {
    @Test
    fun testKing() = testApplication {
        val cell = "b2"
        val expected =
            "Possible moves: \na1\na2\na3\nb1\nb3\nc1\nc2\nc3\n".split("\n").toSet()
        application {
            configureRouting()
        }
        client.get("/king?cell=$cell").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(expected, bodyAsText().split("\n").toSet())
        }
    }

    @Test
    fun testRook() = testApplication {
        val cell = "g7"
        val expected =
            "Possible moves: \ng1\ng2\ng3\ng4\ng5\ng6\ng8\na7\nb7\nc7\nd7\nf7\ne7\nh7\n".split("\n").toSet()
        application {
            configureRouting()
        }
        client.get("/rook?cell=$cell").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(expected, bodyAsText().split("\n").toSet())
        }
    }

    @Test
    fun testBishop() = testApplication {
        val cell = "h1"
        val expected =
            "Possible moves: \ng2\nf3\ne4\nd5\nc6\nb7\na8\n".split("\n").toSet()
        application {
            configureRouting()
        }
        client.get("/bishop?cell=$cell").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(expected, bodyAsText().split("\n").toSet())
        }
    }

    @Test
    fun testKnight() = testApplication {
        val cell = "a8"
        val expected =
            "Possible moves: \nb6\nc7\n".split("\n").toSet()
        application {
            configureRouting()
        }
        client.get("/knight?cell=$cell").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(expected, bodyAsText().split("\n").toSet())
        }
    }

    @Test
    fun testIncorrect() = testApplication {
        application {
            configureRouting()
        }
        client.get("/rook?cell=a9").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Incorrect cell", bodyAsText())
        }
    }

    @Test
    fun testEmptyParameter() = testApplication {
        application {
            configureRouting()
        }
        client.get("/bishop").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Empty parameter", bodyAsText())
        }
    }
}