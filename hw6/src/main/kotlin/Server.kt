import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*
import kotlinx.serialization.Serializable
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.jvmErasure

@Serializable
class Product(
    val name: String,
    val quantity: Int,
    val price: Double,
    val country: CountryOfProduction
)

@Serializable
class User(
    val id: Int,
    val nickname: String,
    val password: String,
    val status: Status
)

enum class CountryOfProduction {
    China,
    Russia,
    USA,
    Germany,
    Japan,
    Canada,
    India,
    Unknown
}

enum class Status {
    User,
    Moderator,
    Administrator,
}

inline fun <reified T : Any> HTML.form(obj: T) {
    head {
        title { +"HTML forms" }
    }
    body {
        div {
            T::class
                .memberProperties
                .map {
                    div {
                        val v = it.call(obj)
                        if (it.returnType.jvmErasure.java.isEnum) {
                            select {
                                it.returnType.jvmErasure.java.enumConstants.forEach {
                                    option {
                                        if (it.toString() == v.toString()) selected = true
                                        text(it.toString())
                                    }
                                }
                            }
                        } else {
                            textInput {
                                value = v.toString()
                            }
                        }
                    }
                }
        }
    }
}

fun HTML.productDefaultForm() = form(Product("milk", 3, 65.99, CountryOfProduction.Russia))

fun HTML.userDefaultForm() = form(User(1, "fertdt", "qwerty", Status.User))

fun Application.routing() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/productForm") {
            call.respondHtml(HttpStatusCode.OK, HTML::productDefaultForm)
        }
        post("/product") {
            val parameters = call.receiveParameters()
            val name = parameters["name"] ?: "Unknown"
            val quantity = parameters["quantity"]?.toInt() ?: 0
            val price = parameters["price"]?.toDouble() ?: 0.0
            val countryOfProduction =
                when (parameters["countryOfProduction"]) {
                    "China" -> CountryOfProduction.China
                    "Russia" -> CountryOfProduction.Russia
                    "USA" -> CountryOfProduction.USA
                    "Germany" -> CountryOfProduction.Germany
                    "Japan" -> CountryOfProduction.Japan
                    "Canada" -> CountryOfProduction.Canada
                    "India" -> CountryOfProduction.India
                    else -> CountryOfProduction.Unknown
                }

            fun HTML.productForm() = form(Product(name, quantity, price, countryOfProduction))
            call.respondHtml(HttpStatusCode.OK, HTML::productForm)
        }
        get("/userForm") {
            call.respondHtml(HttpStatusCode.OK, HTML::userDefaultForm)
        }
        post("/user") {
            val parameters = call.receiveParameters()
            val id = parameters["id"]?.toInt() ?: 0
            val nickname = parameters["nickname"] ?: "Unknown"
            val password = parameters["password"] ?: "Unknown"
            val status =
                when (parameters["status"]) {
                    "Moderator" -> Status.Moderator
                    "Administrator" -> Status.Administrator
                    else -> Status.User
                }

            fun HTML.userForm() = form(User(id, nickname, password, status))
            call.respondHtml(HttpStatusCode.OK, HTML::userForm)
        }
    }
}

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        routing()
    }.start(wait = true)
}
