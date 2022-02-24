package gecko.android.adapter

import gecko.android.model.PersistedResponse
import gecko.model.Response

internal interface ResponseAdapter {
    fun adapt(id: Long, response: Response): PersistedResponse
}