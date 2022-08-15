package gecko.model.decorator

import gecko.Base64Encoder
import gecko.model.ByteData

internal class ByteDataBase64Decorator(
    private val origin: ByteData,
    private val encoder: Base64Encoder = Base64Encoder
) : ByteData() {

    override val value: ByteArray
        get() = origin.value.toBase64()

    private fun ByteArray.toBase64(): ByteArray {
        return encoder.encode(this)
    }

}

internal fun ByteData.asBase64(): ByteData {
    return ByteDataBase64Decorator(this)
}