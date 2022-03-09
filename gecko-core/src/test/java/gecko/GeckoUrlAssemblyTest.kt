package gecko

import com.google.common.truth.Truth.assertThat
import gecko.model.asString
import gecko.model.asTail
import gecko.model.networkMetadata
import gecko.test.TestBlueprint
import gecko.test.TestSuccessful
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.kotlin.whenever

class GeckoUrlAssemblyTest : TestBlueprint {

    private val domain: String = "my-domain.org"
    private lateinit var assembly: GeckoUrlAssembly

    @Mock
    private lateinit var source: Gecko

    @BeforeEach
    override fun prepare() {
        super.prepare()
        assembly = GeckoUrlAssembly(source, domain)
    }

    @Test
    fun `call source`() {
        val metadata = networkMetadata()
        whenever(source.process(metadata)).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            assembly.process(metadata)
        }
    }

    @Test
    fun `returns assembled url`() {
        val metadata = networkMetadata()
        val query = "my-awesome-data"
        whenever(source.process(metadata)).thenReturn(query.asTail())

        val output = assembly.process(metadata).asString()
        assertThat(output).isEqualTo("https://$domain/?q=$query")
    }

}