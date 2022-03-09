package gecko

import gecko.model.asTail
import gecko.model.networkMetadata
import gecko.test.TestBlueprint
import gecko.test.TestSuccessful
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.kotlin.whenever

class GeckoLoggingTest : TestBlueprint {

    private lateinit var logging: GeckoLogging

    @Mock
    private lateinit var source: Gecko

    @Mock
    private lateinit var logger: Logger

    @BeforeEach
    override fun prepare() {
        super.prepare()
        logging = GeckoLogging(source, logger)
    }

    @Test
    fun `calls source`() {
        val metadata = networkMetadata()
        whenever(source.process(metadata)).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            logging.process(metadata)
        }
    }

    @Test
    fun `logs method, code and url`() {
        val metadata = networkMetadata()
        whenever(source.process(metadata)).thenReturn("".asTail())
        whenever(logger.log("-> ${metadata.request.method} | ${metadata.response.code} | ${metadata.request.url}"))
            .thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            logging.process(metadata)
        }
    }

    @Test
    fun `logs source output`() {
        val metadata = networkMetadata()
        val expected = "woohoo!"
        whenever(source.process(metadata)).thenReturn(expected.asTail())
        whenever(logger.log("-> ${metadata.request.method} | ${metadata.response.code} | ${metadata.request.url}"))
            .thenAnswer {}
        whenever(logger.log(expected)).thenThrow(TestSuccessful())
    }

}