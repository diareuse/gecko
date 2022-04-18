package gecko

import com.google.common.truth.Truth.assertThat
import gecko.model.asString
import gecko.model.networkMetadata
import gecko.test.TestBlueprint
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

internal class GeckoFormatterTest : TestBlueprint {

    private lateinit var formatter: GeckoFormatter

    @Mock
    private lateinit var adapter: MetadataAdapter

    @BeforeEach
    override fun prepare() {
        super.prepare()
        formatter = GeckoFormatter(adapter)
    }

    @Test
    fun `returns adapter's output`() {
        val metadata = networkMetadata()
        val expected = "output"
        whenever(adapter.adapt(metadata)).thenReturn(expected)
        val output = formatter.process(metadata)
        assertThat(output.asString()).isEqualTo(expected)
    }

}