package gecko

import gecko.model.networkMetadata
import gecko.test.TestBlueprint
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

internal class GeckoLoggingTest : TestBlueprint {

    private val domain: String = "my-domain.org"

    @Mock
    private lateinit var logger: Logger
    private lateinit var assembly: GeckoLogging

    @BeforeEach
    override fun prepare() {
        super.prepare()
        assembly = GeckoLogging()
        assembly.logger = logger
    }

    @Test
    fun `returns assembled url`() {
        val metadata = networkMetadata()
        val query = "my-awesome-data"
        whenever(logger.log("https://$domain/?q=$query")).then {}
        assembly.process(metadata)
        // fixme this test passes because the stubbed method is called after the test ends in cached thread pool somewhere
        // this is wrong and should be fixed
    }

}