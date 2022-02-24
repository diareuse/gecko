package gecko.android.adapter

import gecko.android.model.PersistedCall

internal class CallAdapterDefault : CallAdapter {

    override fun adapt(output: ByteArray): PersistedCall {
        return PersistedCall(link = output.decodeToString())
    }

}