package gecko

import com.google.common.truth.Truth.assertThat
import gecko.test.TestBlueprint
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Base64EncoderJavaTest : TestBlueprint {

    private lateinit var encoder: Base64EncoderJava

    @BeforeEach
    override fun prepare() {
        super.prepare()
        encoder = Base64EncoderJava()
    }

    @Test
    fun `encodes to base64`() {
        val data = "hello world!"
        val expected = "aGVsbG8gd29ybGQhCg=="

        assertThat(encoder.encode(data.encodeToByteArray()).decodeToString()).isEqualTo(expected)
    }

}