package gecko.model.interceptor

import com.google.common.truth.Truth.assertThat
import gecko.model.ByteData
import gecko.model.ByteDataStage
import gecko.model.decorator.ByteDataBase64Decorator
import gecko.test.TestBlueprint
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ByteDataInterceptorEncodingTest : TestBlueprint {

    private lateinit var interceptor: ByteDataInterceptorEncoding

    @BeforeEach
    override fun prepare() {
        super.prepare()
        interceptor = ByteDataInterceptorEncoding()
    }

    @Test
    fun `matches stage`() {
        assertThat(interceptor.stage).isEqualTo(ByteDataStage.Encoding)
    }

    @Test
    fun `returns encoded data`() {
        assertThat(interceptor.intercept(ByteData.from(""))).isInstanceOf(ByteDataBase64Decorator::class.java)
    }

}