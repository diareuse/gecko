package gecko.model.effect

import gecko.Logger
import gecko.model.Response
import gecko.model.ResponseDecorated

internal class ResponseLoggingSideEffect(origin: Response) : ResponseDecorated(origin) {

    init {
        Logger.log("<- $code")
        Logger.log(body.decodeToString().ifBlank { "<empty-body>" })
    }

}

internal fun Response.withLogging(): Response {
    return ResponseLoggingSideEffect(this)
}