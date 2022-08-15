package gecko.okhttp

import com.google.auto.service.AutoService
import gecko.Gecko
import gecko.model.NetworkMetadata

@AutoService(Gecko::class)
object GeckoTest : Gecko {

    private val listeners: MutableList<(NetworkMetadata) -> Unit> = mutableListOf()

    override fun process(metadata: NetworkMetadata) {
        listeners.forEach {
            it(metadata)
        }
    }

    fun addListener(listener: (NetworkMetadata) -> Unit): (NetworkMetadata) -> Unit {
        listeners += listener
        return listener
    }

    fun removeListener(listener: (NetworkMetadata) -> Unit) {
        listeners -= listener
    }

}