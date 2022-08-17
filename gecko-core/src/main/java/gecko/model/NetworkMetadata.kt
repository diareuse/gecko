package gecko.model

import com.google.auto.value.AutoValue

/**
 * Helper class wrapping both Request and Response to a singular data entry.
 * @see Request
 * @see Response
 * */
@AutoValue
abstract class NetworkMetadata {

    abstract val request: Request
    abstract val response: Response

    companion object {

        operator fun invoke(request: Request, response: Response): NetworkMetadata = AutoValue_NetworkMetadata(
            Request.wrap(request),
            Response.wrap(response)
        )

    }

}
