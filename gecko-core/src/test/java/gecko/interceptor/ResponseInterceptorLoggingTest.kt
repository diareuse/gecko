package gecko.interceptor

import com.google.common.truth.Truth.assertThat
import gecko.model.effect.ResponseLoggingSideEffect
import gecko.model.response
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ResponseInterceptorLoggingTest {

    private lateinit var interceptor: ResponseInterceptorLogging

    @BeforeEach
    fun setUp() {
        interceptor = ResponseInterceptorLogging()
    }

    @Test
    fun `has required type`() {
        val result = interceptor.intercept(response())
        assertThat(result).isInstanceOf(ResponseLoggingSideEffect::class.java)
    }

}