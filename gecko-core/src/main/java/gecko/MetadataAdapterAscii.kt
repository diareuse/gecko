package gecko

import gecko.model.Headers
import gecko.model.NetworkMetadata
import gecko.model.Request
import gecko.model.Response

/**
 * Uses basic ASCII characters to define a proprietary format for storing information
 * */
class MetadataAdapterAscii : MetadataAdapter {

    override fun adapt(metadata: NetworkMetadata) = StringBuilder()
        .append(adapt(metadata.request))
        .append(adapt(metadata.response))
        .append(EOT)
        .toString()

    private fun adapt(request: Request) = StringBuilder()
        .append(RequestMethod, request) { method }
        .append(RequestUrl, request) { url }
        .append(RequestHeaders, request) { headers.stringify() }
        .append(RequestLength, request) { length }
        .append(RequestContentType, request) { contentType }
        .append(RequestBody, request) { body.decodeToString() }
        .toString()

    private fun adapt(response: Response) = StringBuilder()
        .append(ResponseCode, response) { code }
        .append(ResponseMessage, response) { message }
        .append(ResponseProtocol, response) { protocol }
        .append(ResponseHeaders, response) { headers.stringify() }
        .append(ResponseLength, response) { length }
        .append(ResponseContentType, response) { contentType }
        .append(ResponseBody, response) { body.decodeToString() }
        .toString()

    private inline fun <T> StringBuilder.append(type: Char, field: T, selector: T.() -> Any) =
        append(field.stringify(type, selector))

    private inline fun <T> T.stringify(type: Char, body: T.() -> Any): String =
        "$TextStart$type${body()}$TextEnd"

    private fun Headers.stringify() =
        joinToString("\n") { "${it.first}:${it.second}" }

    companion object {

        private const val RequestMethod = 'm'
        private const val RequestUrl = 'u'
        private const val RequestHeaders = 'h'
        private const val RequestLength = 'l'
        private const val RequestContentType = 't'
        private const val RequestBody = 'b'

        private const val ResponseCode = 'c'
        private const val ResponseMessage = 'M'
        private const val ResponseProtocol = 'p'
        private const val ResponseHeaders = 'H'
        private const val ResponseLength = 'L'
        private const val ResponseContentType = 'T'
        private const val ResponseBody = 'B'

        private const val TextStart = 2.toChar()
        private const val TextEnd = 3.toChar()
        private const val EOT = 4.toChar()

    }

}