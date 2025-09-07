import com.example.models.Ingredient
import com.example.models.Instruction
import com.example.models.Recipe
import com.example.module
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.*
import org.junit.Test

class ApplicationKtTest {
    @Test
    fun newRecipeCanBeAdded() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json() } }

        val recipe =
            Recipe(
                "5",
                "Pasta aglio e olio",
                "Buy a bread",
                createdAt = "2024-1-2",
                updatedAt = "2024-1-2",
                authorId = "1",
                coverImageUrl = "",
                ingredients = listOf(Ingredient("1", "Bread", "testing", 1)),
                instructions = listOf(Instruction("1", 2, "test", "d")),
            )

        val response =
            client.post("/recipes") {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                setBody(recipe)
            }
        assertEquals(HttpStatusCode.Created, response.status)
    }
}
