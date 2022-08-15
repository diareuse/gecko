package gecko.android.model

import gecko.model.Request

internal fun Request.snapshotRequest(id: Long): PersistedRequest {
    return PersistedRequest(
        idCall = id,
        method = method,
        url = url,
        headers = headers,
        length = length,
        contentType = contentType,
        body = body.decodeToString()
    )
}