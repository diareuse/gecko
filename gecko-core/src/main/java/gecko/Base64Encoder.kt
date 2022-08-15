package gecko

import gecko.util.loadService

/**
 * Convenience functional interface for platforms to wrap their native Base64 encoders with.
 * This is particularly useful for Android versions which don't have native Java encoder.
 * */
interface Base64Encoder {

    /**
     * Encodes given bytes and returns URL compliant Base64 sequence.
     * */
    fun encode(data: ByteArray): ByteArray

    companion object : Base64Encoder {

        override fun encode(data: ByteArray): ByteArray {
            return loadService<Base64Encoder>().encode(data)
        }

    }

}