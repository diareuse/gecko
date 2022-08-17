package gecko.model.decorator

import com.google.common.truth.Truth.assertThat
import gecko.Base64Encoder
import gecko.model.ByteData
import gecko.test.TestBlueprint
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

internal class ByteDataBase64DecoratorTest : TestBlueprint {

    @Mock
    private lateinit var encoder: Base64Encoder
    private lateinit var data: ByteDataBase64Decorator

    @BeforeEach
    override fun prepare() {
        super.prepare()
        val expected = "aaaa"
        val expectedBytes = expected.encodeToByteArray()
        data = ByteDataBase64Decorator(ByteData.from(expected), encoder)
        whenever(encoder.encode(expectedBytes)).thenReturn(expectedBytes)
    }

    @Test
    fun `encodes data`() {
        assertThat(data.value.decodeToString()).isEqualTo("aaaa")
    }

}