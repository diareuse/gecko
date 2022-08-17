package gecko.model


/**
 * Uses provided [origin] to return its values as implementation of this class. Is effectively a delegate to [origin].
 * */
abstract class ByteDataDecorated(private val origin: ByteData) : ByteData() {

    override val value: ByteArray
        get() = origin.value

}