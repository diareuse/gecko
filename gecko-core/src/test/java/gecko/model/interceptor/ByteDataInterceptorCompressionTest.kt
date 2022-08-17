package gecko.model.interceptor

import com.google.common.truth.Truth.assertThat
import gecko.model.ByteData
import gecko.model.ByteDataStage
import gecko.model.decorator.ByteDataCompressionDecorator
import gecko.test.TestBlueprint
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ByteDataInterceptorCompressionTest : TestBlueprint {

    private lateinit var interceptor: ByteDataInterceptorCompression

    @BeforeEach
    override fun prepare() {
        super.prepare()
        interceptor = ByteDataInterceptorCompression()
    }

    @Test
    fun `matches stage`() {
        assertThat(interceptor.stage).isEqualTo(ByteDataStage.Compression)
    }

    @Test
    fun `returns compressed data`() {
        assertThat(interceptor.intercept(ByteData.from(""))).isInstanceOf(ByteDataCompressionDecorator::class.java)
    }

}