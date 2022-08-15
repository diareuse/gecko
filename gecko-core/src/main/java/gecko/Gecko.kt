package gecko

import gecko.model.NetworkMetadata
import gecko.util.loadServices

/**
 * Gecko entry point. Uses given metadata to generate a result in a form of [Tail].
 *
 * It should always do at most one transformation and then return the result. The reason being that
 * upcoming versions may or may not cache the steps, therefore making the steps as granular as
 * possible is strongly suggested.
 *
 * Metadata can also be manipulated by former steps to allow for truncating or obfuscating
 * sensitive information.
 * */
interface Gecko {

    /**
     * Manipulates either [metadata] or result of a previous step and returns the updated result.
     * */
    fun process(metadata: NetworkMetadata)

    companion object : Gecko {

        override fun process(metadata: NetworkMetadata) {
            for (gecko in loadServices<Gecko>()) {
                gecko.process(metadata)
            }
        }

    }

}