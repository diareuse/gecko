package gecko

import com.google.common.truth.Truth.assertThat
import gecko.test.TestBlueprint
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TextCompressorGzipTest : TestBlueprint {

    private lateinit var compressor: TextCompressorGzip

    @BeforeEach
    override fun prepare() {
        super.prepare()
        compressor = TextCompressorGzip()
    }

    @Test
    fun `returns compressed bytes`() {
        val output = compressor.compress("hello world!".encodeToByteArray())
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
                -1,
                -53,
                72,
                -51,
                -55,
                -55,
                87,
                40,
                -49,
                47,
                -54,
                73,
                81,
                4,
                0,
                109,
                -62,
                -76, 3, 12, 0, 0, 0
            )
        )
    }

}