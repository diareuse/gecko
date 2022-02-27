package gecko

fun interface Base64Encoder {
    fun encode(data: ByteArray): ByteArray
}