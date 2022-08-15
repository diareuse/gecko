package gecko.android.model

import gecko.model.Response

internal fun Response.snapshotResponse(id: Long): PersistedResponse {
    return PersistedResponse(
        idCall = id,
        code = code,
        message = message,
        protocol = protocol,
        headers = headers,
        length = length,
        contentType = contentType,
        body = body.decodeToString(),
    )
}