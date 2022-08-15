package gecko.model.decorator

import gecko.TextCompressor
import gecko.model.ByteData
import gecko.model.ByteDataDecorated

internal class ByteDataCompressionDecorator(
    origin: ByteData
) : ByteDataDecorated(origin) {

    override val value: ByteArray
        get() = super.value.toCompressed()

    private fun ByteArray.toCompressed(): ByteArray {
        return TextCompressor.compress(this)
    }

}

internal fun ByteData.asCompressed(): ByteData {
    return ByteDataCompressionDecorator(this)
}