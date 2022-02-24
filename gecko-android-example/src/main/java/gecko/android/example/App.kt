package gecko.android.example

import android.app.Application
import android.util.Log
import gecko.Gecko
import gecko.GeckoBuilder
import gecko.GeckoLogging
import gecko.android.GeckoPersisted
import gecko.model.NetworkMetadata
import gecko.model.Request
import gecko.model.Response
import kotlin.random.Random.Default.nextBytes
import kotlin.random.Random.Default.nextInt

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        gecko = GeckoBuilder.getDefault()
            .step { GeckoPersisted(it, this) }
            .step { GeckoLogging(it) { output -> Log.v("Gecko", output) } }
            .build()

        Thread {
            repeat(100) {
                gecko.process(generateMetadata())
            }
        }.start()
    }

    private fun generateMetadata() = NetworkMetadata(
        request = generateRequest(),
        response = generateResponse()
    )

    private fun generateRequest() = Request(
        method = httpMethods.random(),
        url = "https://api.github.com/v3/foo/bar/",
        headers = listOf("Authorization" to "Bearer", "User-Agent" to "Gecko"),
        length = 0,
        body = { it.write(nextBytes(nextInt(10, 1000))) }
    )

    private fun generateResponse() = Response(
        code = (200 until 600).random(),
        message = "OK",
        protocol = "HTTP/2",
        headers = listOf("X-Hello" to "*waves*"),
        length = 0,
        body = { it.write(nextBytes(nextInt(10, 1000))) },
    )

    companion object {

        lateinit var gecko: Gecko
        val httpMethods = listOf("GET", "POST", "HEAD", "DELETE", "PUT", "PATCH")

    }

}