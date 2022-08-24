package gecko.okhttp

import gecko.okhttp.model.request
import gecko.okhttp.model.toRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

internal class OkHttpResponseAdapterTest {

    @Mock
    private lateinit var body: ResponseBody
    private lateinit var response: Response

    @BeforeEach
    fun prepare() {
        MockitoAnnotations.openMocks(this).close()
        response = Response.Builder()
            .code(1)
            .request(request().toRequest())
            .protocol(Protocol.HTTP_1_0)
            .message("")
            .body(body)
            .build()
        whenever(body.source()).thenReturn(Buffer())
    }

    @Test
    fun getCode() {
        assertEquals(response.code, response.asResponse().code)
    }

    @Test
    fun getMessage() {
        assertEquals(response.message, response.asResponse().message)
    }

    @Test
    fun getProtocol() {
        assertEquals(response.protocol.name, response.asResponse().protocol)
    }

    @Test
    fun getHeaders() {
        assertEquals(response.headers, response.asResponse().headers)
    }

    @Test
    fun getLength() {
        whenever(body.contentLength()).thenReturn(100)
        assertEquals(response.body!!.contentLength(), response.asResponse().length)
    }

    @Test
    fun getContentType() {
        whenever(body.contentType()).thenReturn("application/json".toMediaTypeOrNull())
        assertEquals(response.body!!.contentType().toString(), response.asResponse().contentType)
    }

    @Test
    fun getBody() {
        val stream = "foo".encodeToByteArray()
        val buffer = Buffer().write(stream)
        whenever(body.contentLength()).thenReturn(stream.size.toLong())
        whenever(body.source()).thenReturn(buffer)
        assertArrayEquals(stream, response.asResponse().body)
    }

    @Test
    fun getBodyReadsImmediately() {
        whenever(body.source()).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            response.asResponse()
        }
    }

}