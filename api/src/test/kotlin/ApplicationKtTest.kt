import com.example.module
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.junit.Assert.assertEquals
import org.junit.Test

class ApplicationKtTest {
     @Test
     fun testRoot() = testApplication {
         application {
             module()
         }

         val response = client.get("/")
         assertEquals(HttpStatusCode.OK, response.status)
         assertEquals("Taste and Tell", response.bodyAsText())
     }
 }