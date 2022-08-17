package gecko.model.effect

import gecko.Logger
import gecko.model.Response
import gecko.test.TestBlueprint
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

internal class ResponseLoggingSideEffectTest : TestBlueprint {

    private lateinit var response: Response

    @Mock
    private lateinit var logger: Logger

    @BeforeEach
    override fun prepare() {
        super.prepare()
        response = Response(200, "", "HTTP_2", emptyList(), 0, "", byteArrayOf())
    }

    @Test
    fun `logs data`() {
        whenever(logger.log("<- ${response.code}")).then {}
        whenever(logger.log("<empty-body>")).then {}
        response.withLogging()
    }

    @Test
    fun `logs data with body`() {
        whenever(logger.log("<- ${response.code}")).then {}
        whenever(logger.log("my body")).then {}
        response.snapshot(body = "my body".encodeToByteArray()).withLogging()
    }

}