package gecko.android.adapter

import gecko.android.model.PersistedRequest
import gecko.model.Request

internal class RequestAdapterDefault : RequestAdapter {

    override fun adapt(id: Long, request: Request) = request.run {
        PersistedRequest(
            idCall = id,
            method = method,
            url = url,
            headers = headers,
            length = length,
            body = body.decodeToString()
        )
    }

}