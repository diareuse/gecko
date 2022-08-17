package gecko.okhttp

import gecko.Gecko
import gecko.okhttp.model.*
import okhttp3.Interceptor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

internal class GeckoInterceptorTest {

    private lateinit var interceptor: GeckoInterceptor

    @Mock
    private lateinit var chain: Interceptor.Chain

    @Mock
    private lateinit var gecko: Gecko

    @BeforeEach
    fun prepare() {
        MockitoAnnotations.openMocks(this).close()
        interceptor = GeckoInterceptor()
        interceptor.gecko = gecko
    }

    @Test
    fun `passes correctly parsed request and response`() {
        val request = request()
        val response = response()
        val expected = networkMetadata(request, response)
        val okhttpRequest = request.toRequest()

        whenever(chain.request()).thenReturn(okhttpRequest)
        whenever(chain.proceed(okhttpRequest)).thenReturn(response.toResponse(okhttpRequest))
        whenever(gecko.process(expected)).thenThrow(TestSuccessful())

        assertThrows<TestSuccessful> {
            interceptor.intercept(chain)
        }
    }

    @Test
    fun `passes correctly parsed request and response with g-zip`() {
        val request = request()
        val responseBody = "aaaa".toByteArray()
        val responseBodyRaw = byteArrayOf(
            31, -117, 8, 0, 0, 0, 0, 0, 0, 0, 75, 76, 76, 76, 4, 0, 69, -27, -104, -83, 4, 0, 0, 0
        )
        val response = response(
            body = responseBody,
            length = responseBodyRaw.size.toLong(),
            headers = listOf("Content-Encoding" to "gzip")
        )
        val expected = networkMetadata(request, response)
        val okhttpRequest = request.toRequest()
        whenever(chain.request()).thenReturn(okhttpRequest)
        whenever(chain.proceed(okhttpRequest)).thenReturn(
            response.copy(body = responseBodyRaw).toResponse(okhttpRequest)
        )
        whenever(gecko.process(expected)).thenThrow(TestSuccessful())

        assertThrows<TestSuccessful> {
            interceptor.intercept(chain)
        }
    }

}
