package gecko

/**
 * Convenience functional interface for platforms to wrap their native Base64 encoders with.
 * This is particularly useful for Android versions which don't have native Java encoder.
 * */
fun interface Base64Encoder {

    /**
     * Encodes given bytes and returns URL compliant Base64 sequence.
     * */
    fun encode(data: ByteArray): ByteArray

}