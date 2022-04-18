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

internal class GeckoBase64WrapperTest : TestBlueprint {

    private lateinit var wrapper: GeckoBase64Wrapper

    @Mock
    private lateinit var source: Gecko

    @Mock
    private lateinit var encoder: Base64Encoder

    @BeforeEach
    override fun prepare() {
        super.prepare()
        wrapper = GeckoBase64Wrapper(source, encoder)
    }

    @Test
    fun `calls source`() {
        val metadata = networkMetadata()
        whenever(source.process(metadata)).thenThrow(TestSuccessful())

        assertThrows<TestSuccessful> {
            wrapper.process(metadata)
        }
    }

    @Test
    fun `returns encoder's output`() {
        val metadata = networkMetadata()
        val bytes = ByteArray(1)
        whenever(source.process(metadata)).thenReturn(Tail.Bytes(bytes))
        whenever(encoder.encode(bytes)).thenReturn(bytes)

        val result = wrapper.process(metadata).asByteArray()

        assertThat(result).isSameInstanceAs(bytes)
    }

}