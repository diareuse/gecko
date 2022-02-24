package gecko.android.adapter

import gecko.android.model.CallMetadataView
import gecko.android.model.CallView
import gecko.android.model.GeckoData
import gecko.android.model.GeckoMetadata
import gecko.model.Headers

internal class MetadataAdapterDefault : MetadataAdapter {

    override fun adapt(view: CallView) = view.run {
        GeckoData(
            id = id,
            link = link,
            createdAt = createdAt,
            requestMethod = requestMethod,
            requestUrl = requestUrl,
            requestHeaders = requestHeaders.asSet(),
            requestLength = requestLength,
            requestBody = requestBody,
            responseCode = responseCode,
            responseMessage = responseMessage,
            responseProtocol = responseProtocol,
            responseHeaders = responseHeaders.asSet(),
            responseLength = responseLength,
            responseBody = responseBody
        )
    }

    override fun adapt(view: CallMetadataView) = view.run {
        GeckoMetadata(
            id = id,
            createdAt = createdAt,
            requestMethod = requestMethod,
            requestUrl = requestUrl,
            requestLength = requestLength,
            responseCode = responseCode,
            responseLength = responseLength
        )
    }

    private fun Headers.asSet() = asSequence()
        .map { (key, value) -> "$key: $value" }
        .toSet()

}