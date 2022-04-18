package gecko.model

internal fun networkMetadata(
    request: Request = request(),
    response: Response = response()
) = NetworkMetadata(
    request = request,
    response = response
)
