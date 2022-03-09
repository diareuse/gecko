package gecko

import com.google.common.truth.Truth.assertThat
import gecko.model.Tail
import gecko.model.asByteArray
import gecko.model.networkMetadata
import gecko.test.TestBlueprint
import gecko.test.TestSuccessful
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.kotlin.whenever

class GeckoCompressorTest : TestBlueprint {

    private lateinit var compressor: GeckoCompressor

    @Mock
    private lateinit var source: Gecko

    @BeforeEach
    override fun prepare() {
        super.prepare()
        compressor = GeckoCompressor(source)
    }

    @Test
    fun `calls source`() {
        val metadata = networkMetadata()
        whenever(source.process(metadata)).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            compressor.process(metadata)
        }
    }

    @Test
    fun `returns compressed bytes`() {
        val metadata = networkMetadata()
        whenever(source.process(metadata)).thenReturn(Tail.Text("aaaa"))
        val output = compressor.process(metadata).asByteArray()
        assertThat(output).isEqualTo(
            byteArrayOf(
                31,
                -117,
                8,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                75,
                76,
                76,
                76,
                4,
                0,
                69,
                -27,
                -104,
                -83,
                4,
                0,
                0,
                0
            )
        )
    }

}