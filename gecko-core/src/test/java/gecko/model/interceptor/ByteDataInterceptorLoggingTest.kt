package gecko.model.interceptor

import com.google.common.truth.Truth.assertThat
import gecko.Logger
import gecko.model.ByteData
import gecko.test.TestBlueprint
import gecko.test.TestSuccessful
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.kotlin.whenever

internal class ByteDataInterceptorLoggingTest : TestBlueprint {

    @Mock
    private lateinit var logger: Logger
    private lateinit var interceptor: ByteDataInterceptorLogging

    @BeforeEach
    override fun prepare() {
        super.prepare()
        interceptor = ByteDataInterceptorLogging()
        interceptor.logger = logger
    }

    @Test
    fun `logs input data`() {
        val query = "my-awesome-data"
        whenever(logger.log(query)).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            interceptor.intercept(ByteData.from(query))
        }
    }

    @Test
    fun `returns unmodified data`() {
        val query = "my-awesome-data"
        val expected = ByteData.from(query)
        whenever(logger.log(query)).then {}
        val result = interceptor.intercept(expected)
        assertThat(result).isSameInstanceAs(expected)
    }

}