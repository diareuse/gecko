package gecko

import gecko.model.NetworkMetadata
import gecko.util.loadServices
import java.util.*

/**
 * Gecko entry point. Uses given metadata to process given information.
 * */
interface Gecko {

    /**
     * Processes [metadata] supplied as a parameter.
     * */
    fun process(metadata: NetworkMetadata)

    /**
     * Static implementation uses [ServiceLoader] to find all [Gecko] service implementations.
     * */
    companion object : Gecko {

        override fun process(metadata: NetworkMetadata) {
            for (gecko in loadServices<Gecko>()) {
                gecko.process(metadata)
            }
        }

    }

}