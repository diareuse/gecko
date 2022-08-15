package gecko.model.adapter

import com.google.common.truth.Truth.assertThat
import gecko.model.ByteData
import gecko.model.NetworkMetadata
import gecko.model.networkMetadata
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ByteDataNetworkMetadataAdapterTest {

    private lateinit var metadata: NetworkMetadata
    private lateinit var byteData: ByteData

    @BeforeEach
    fun prepare() {
        metadata = networkMetadata()
        byteData = metadata.asByteData()
    }

    @Test
    fun `ends with EOT`() {
        assertThat(byteData.value.last()).isEqualTo(4.toChar())
    }

    @Test
    fun `contains RequestMethod`() {
        assertContains('m', metadata.request.method)
    }

    @Test
    fun `contains RequestUrl`() {
        assertContains('u', metadata.request.url)
    }

    @Test
    fun `contains RequestHeaders`() {
        assertContains('h', metadata.request.headers.joinToString(separator = "\n") { "${it.first}:${it.second}" })
    }

    @Test
    fun `contains RequestLength`() {
        assertContains('l', metadata.request.length.toString())
    }

    @Test
    fun `contains RequestContentType`() {
        assertContains('t', metadata.request.contentType)
    }

    @Test
    fun `contains RequestBody`() {
        assertContains('b', metadata.request.body.decodeToString())
    }

    @Test
    fun `contains ResponseCode`() {
        assertContains('c', metadata.response.code.toString())
    }

    @Test
    fun `contains ResponseMessage`() {
        assertContains('M', metadata.response.message)
    }

    @Test
    fun `contains ResponseProtocol`() {
        assertContains('p', metadata.response.protocol)
    }

    @Test
    fun `contains ResponseHeaders`() {
        assertContains('H', metadata.response.headers.joinToString(separator = "\n") { "${it.first}:${it.second}" })
    }

    @Test
    fun `contains ResponseLength`() {
        assertContains('L', metadata.response.length.toString())
    }

    @Test
    fun `contains ResponseContentType`() {
        assertContains('T', metadata.response.contentType)
    }

    @Test
    fun `contains ResponseBody`() {
        assertContains('B', metadata.response.body.decodeToString())
    }

    // ---

    private fun assertContains(tag: Char, value: String) {
        val start = 2.toChar()
        val end = 3.toChar()
        assertThat(byteData.value.decodeToString()).contains("${start}${tag}${value}${end}")
    }

}