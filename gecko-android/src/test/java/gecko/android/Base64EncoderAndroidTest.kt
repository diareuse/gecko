package gecko.android

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Base64EncoderAndroidTest {

    private lateinit var encoder: Base64EncoderAndroid

    @BeforeEach
    fun prepare() {
        encoder = Base64EncoderAndroid()
    }

    @Test
    fun `encodes to base64`() {
        val data = "hello world!"
        val expected = "aGVsbG8gd29ybGQh"

        assertThat(encoder.encode(data.encodeToByteArray()).decodeToString()).isEqualTo(expected)
    }

}