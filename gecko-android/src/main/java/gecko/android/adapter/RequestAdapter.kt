package gecko.android.adapter

import gecko.android.model.PersistedRequest
import gecko.model.Request

internal interface RequestAdapter {
    fun adapt(id: Long, request: Request): PersistedRequest
}