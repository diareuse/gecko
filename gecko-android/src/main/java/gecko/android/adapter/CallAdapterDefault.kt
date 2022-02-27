package gecko.android.adapter

import gecko.android.model.PersistedCall

internal class CallAdapterDefault : CallAdapter {

    override fun adapt(output: String): PersistedCall {
        return PersistedCall(link = output)
    }

}