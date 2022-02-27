package gecko.android.adapter

import gecko.android.model.PersistedCall

internal interface CallAdapter {
    fun adapt(output: String): PersistedCall
}