package gecko

/**
 * This is a convenience class for fetching Base64 class from Java. It does so lazily so even
 * instantiating this class will not affect the runtime of a JVM that doesn't contain it.
 * */
class Base64EncoderJava : Base64Encoder {

    private val encode by lazy {
        val base64Class = Class.forName("java.util.Base64")
        base64Class.getMethod("encode", ByteArray::class.java)
    }

    override fun encode(data: ByteArray): ByteArray {
        return encode.invoke(null, data) as ByteArray
    }

}