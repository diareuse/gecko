package gecko.model

fun networkMetadata(
    request: Request = request(),
    response: Response = response()
) = NetworkMetadata(
    request = request,
    response = response
)
