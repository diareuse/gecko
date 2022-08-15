package gecko.model.decorator

import com.google.common.truth.Truth.assertThat
import gecko.model.ByteData
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ByteDataCompressionDecoratorTest {


    private lateinit var data: ByteDataCompressionDecorator

    @BeforeEach
    fun prepare() {
        data = ByteDataCompressionDecorator(ByteData.from("aaaa"))
    }

    @Test
    fun `compresses data`() {
        assertThat(data.value).isEqualTo(
            byteArrayOf(
                31, -117, 8, 0, 0, 0, 0, 0, 0, 0, 75, 76, 76, 76, 4, 0, 69, -27, -104, -83, 4, 0, 0, 0
            )
        )
    }

}