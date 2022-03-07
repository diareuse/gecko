package gecko.android.example

import android.app.Application
import gecko.Gecko
import gecko.android.builder.geckoAndroid
import gecko.model.NetworkMetadata
import gecko.model.Request
import gecko.model.Response
import kotlin.random.Random.Default.nextInt

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        gecko = geckoAndroid()

        Thread {
            repeat(10) {
                gecko.process(generateMetadata())
            }
        }.start()
    }

    private fun generateMetadata() = NetworkMetadata(
        request = generateRequest(),
        response = generateResponse()
    )

    private fun generateRequest(body: String = nextString()) = Request(
        method = httpMethods.random(),
        url = "https://api.github.com/v3/foo/bar/",
        headers = listOf("Authorization" to "Bearer", "User-Agent" to "Gecko"),
        length = body.length.toLong(),
        contentType = "application/json",
        body = body.encodeToByteArray()
    )

    private fun generateResponse(body: String = nextString()) = Response(
        code = (200 until 600).random(),
        message = "OK",
        protocol = "HTTP/2",
        headers = listOf("X-Hello" to "*waves*"),
        length = body.length.toLong(),
        contentType = "application/json",
        body = body.encodeToByteArray()
    )

    companion object {

        lateinit var gecko: Gecko
        private val httpMethods = listOf("GET", "POST", "HEAD", "DELETE", "PUT", "PATCH")
        private val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        private fun nextString(length: Int = nextInt(10, 10000)) = (0 until length).asSequence()
            .map { charPool.random() }
            .joinToString(separator = "")

    }

}