package gecko.model.effect

import gecko.Logger
import gecko.model.Request
import gecko.test.TestBlueprint
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

internal class RequestLoggingSideEffectTest : TestBlueprint {

    private lateinit var request: Request

    @Mock
    private lateinit var logger: Logger

    @BeforeEach
    override fun prepare() {
        super.prepare()
        request = Request("GET", "https://example.org", emptyList(), 0, "application/json", byteArrayOf())
    }

    @Test
    fun `logs data`() {
        whenever(logger.log("-> ${request.method} | ${request.url}")).then {}
        whenever(logger.log("<empty-body>")).then {}
        request.withLogging()
    }

    @Test
    fun `logs data with body`() {
        whenever(logger.log("-> ${request.method} | ${request.url}")).then {}
        whenever(logger.log("my body")).then {}
        request.snapshot(body = "my body".encodeToByteArray()).withLogging()
    }

}