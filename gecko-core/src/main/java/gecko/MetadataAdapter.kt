package gecko

import gecko.model.NetworkMetadata

/**
 * Creates a string representation of a [NetworkMetadata]
 * */
interface MetadataAdapter {

    fun adapt(metadata: NetworkMetadata): String

}