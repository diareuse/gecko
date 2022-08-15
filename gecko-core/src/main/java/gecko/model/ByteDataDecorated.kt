package gecko.model

abstract class ByteDataDecorated(private val origin: ByteData) : ByteData() {

    override val value: ByteArray
        get() = origin.value

}