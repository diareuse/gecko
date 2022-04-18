package gecko

import gecko.model.NetworkMetadata

/**
 * Creates a string representation of a [NetworkMetadata]
 * */
interface MetadataAdapter {

    /**
     * Encodes metadata to a lossless string. Returned value can, but is not required to, be URL
     * safe. Other steps may adjust or modify this data to be compliant.
     * */
    fun adapt(metadata: NetworkMetadata): String

}