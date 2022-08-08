package gecko.android.model

import gecko.model.Tail
import gecko.model.asString

private class PersistedCallAdapter(
    private val tail: Tail
) : PersistedCall() {

    override val link: String
        get() = tail.asString()

}

internal fun Tail.asCall(): PersistedCall {
    return PersistedCallAdapter(this)
}