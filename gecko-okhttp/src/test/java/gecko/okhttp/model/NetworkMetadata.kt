package gecko.okhttp.model

import gecko.model.NetworkMetadata
import gecko.model.Request
import gecko.model.Response

internal fun networkMetadata(
    request: Request = request(),
    response: Response = response()
) = NetworkMetadata(request, response)