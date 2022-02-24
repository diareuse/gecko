package gecko.android.adapter

import gecko.android.model.PersistedResponse
import gecko.android.tooling.readToString
import gecko.model.Response

internal class ResponseAdapterDefault : ResponseAdapter {

    override fun adapt(id: Long, response: Response) = response.run {
        PersistedResponse(
            idCall = id,
            code = code,
            message = message,
            protocol = protocol,
            headers = headers,
            length = length,
            body = body.readToString()
        )
    }

}