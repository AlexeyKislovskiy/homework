import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ServerTest {
    @Test
    fun testUserForm() = withTestApplication(Application::routing) {
        with(handleRequest(HttpMethod.Post, "/user") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
            setBody(
                listOf(
                    "id" to "34",
                    "nickname" to "fertdt",
                    "password" to "qwerty1234",
                    "status" to "User"
                ).formUrlEncode()
            )
        }) {
            val expected = """
                <!DOCTYPE html>
                <html>
                  <head>
                    <title>HTML forms</title>
                  </head>
                  <body>
                    <div>
                      <div><input type="text" value="34"></div>
                      <div><input type="text" value="fertdt"></div>
                      <div><input type="text" value="qwerty1234"></div>
                      <div><select><option selected="selected">User</option><option>Moderator</option><option>Administrator</option></select></div>
                    </div>
                  </body>
                </html>

            """.trimIndent()
            assertEquals(expected, response.content)
        }
    }

    @Test
    fun testDefaultUserForm() {
        withTestApplication(Application::routing) {
            handleRequest(HttpMethod.Get, "/userForm").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val expected = """
                    <!DOCTYPE html>
                    <html>
                      <head>
                        <title>HTML forms</title>
                      </head>
                      <body>
                        <div>
                          <div><input type="text" value="1"></div>
                          <div><input type="text" value="fertdt"></div>
                          <div><input type="text" value="qwerty"></div>
                          <div><select><option selected="selected">User</option><option>Moderator</option><option>Administrator</option></select></div>
                        </div>
                      </body>
                    </html>

                """.trimIndent()
                assertEquals(expected, response.content)
            }
        }
    }

    @Test
    fun testProductForm() = withTestApplication(Application::routing) {
        with(handleRequest(HttpMethod.Post, "/product") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
            setBody(
                listOf(
                    "name" to "bread",
                    "quantity" to "34",
                    "price" to "30.49",
                    "countryOfProduction" to "Germany"
                ).formUrlEncode()
            )
        }) {
            val expected = """
                <!DOCTYPE html>
                <html>
                  <head>
                    <title>HTML forms</title>
                  </head>
                  <body>
                    <div>
                      <div><select><option>China</option><option>Russia</option><option>USA</option><option selected="selected">Germany</option><option>Japan</option><option>Canada</option><option>India</option><option>Unknown</option></select></div>
                      <div><input type="text" value="bread"></div>
                      <div><input type="text" value="30.49"></div>
                      <div><input type="text" value="34"></div>
                    </div>
                  </body>
                </html>

            """.trimIndent()
            assertEquals(expected, response.content)
        }
    }

    @Test
    fun testDefaultProductForm() {
        withTestApplication(Application::routing) {
            handleRequest(HttpMethod.Get, "/productForm").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val expected = """
                    <!DOCTYPE html>
                    <html>
                      <head>
                        <title>HTML forms</title>
                      </head>
                      <body>
                        <div>
                          <div><select><option>China</option><option selected="selected">Russia</option><option>USA</option><option>Germany</option><option>Japan</option><option>Canada</option><option>India</option><option>Unknown</option></select></div>
                          <div><input type="text" value="milk"></div>
                          <div><input type="text" value="65.99"></div>
                          <div><input type="text" value="3"></div>
                        </div>
                      </body>
                    </html>

                """.trimIndent()
                assertEquals(expected, response.content)
            }
        }
    }
}
