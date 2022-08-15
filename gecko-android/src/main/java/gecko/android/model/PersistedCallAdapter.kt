package gecko.android.model

import gecko.model.ByteData

internal fun ByteData.toCall(): PersistedCall {
    return PersistedCall(link = value.decodeToString())
}