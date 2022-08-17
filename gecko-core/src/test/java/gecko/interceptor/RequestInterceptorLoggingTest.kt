package gecko.interceptor

import com.google.common.truth.Truth.assertThat
import gecko.model.effect.RequestLoggingSideEffect
import gecko.model.request
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class RequestInterceptorLoggingTest {

    private lateinit var interceptor: RequestInterceptorLogging

    @BeforeEach
    fun setUp() {
        interceptor = RequestInterceptorLogging()
    }

    @Test
    fun `has required type`() {
        val result = interceptor.intercept(request())
        assertThat(result).isInstanceOf(RequestLoggingSideEffect::class.java)
    }

}