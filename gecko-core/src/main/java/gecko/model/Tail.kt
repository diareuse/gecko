package gecko.model

/**
 * Sealed, unbiased encapsulation of possible values provided to Gecko. It allows for a concise
 * work with the result such as [mapBytes].
 * */
sealed class Tail {

    /** @see Tail */
    class Bytes(val value: ByteArray) : Tail()

    /** @see Tail */
    class Text(val value: String) : Tail()

}

// ---

/**
 * Convenience function to wrap this [String] as [Tail].
 * */
fun String.asTail(): Tail = Tail.Text(this)

/**
 * Convenience function to wrap this [ByteArray] as [Tail].
 * */
fun ByteArray.asTail(): Tail = Tail.Bytes(this)

// ---

/**
 * Convenience function to unwrap this [Tail] to [ByteArray]. Depending on the incoming value,
 * it may encode a [String] to [ByteArray] upon calling this method.
 * */
fun Tail.asByteArray() = when (this) {
    is Tail.Bytes -> value
    is Tail.Text -> value.toByteArray()
}

/**
 * Convenience function to unwrap this [Tail] to [String]. Depending on the incoming value,
 * it may decode a [ByteArray] to [String] upon calling this method.
 * */
fun Tail.asString() = when (this) {
    is Tail.Bytes -> value.decodeToString()
    is Tail.Text -> value
}

// ---

/**
 * Convenience function for mapping bytes.
 * @see asByteArray
 * @see asTail
 * */
@JvmName("mapBytesToBytes")
inline fun Tail.mapBytes(mapper: (ByteArray) -> ByteArray): Tail {
    val input = asByteArray()
    val output = mapper(input)
    return output.asTail()
}

/**
 * Convenience function for mapping bytes.
 * @see asByteArray
 * @see asTail
 * */
@JvmName("mapBytesToString")
inline fun Tail.mapBytes(mapper: (ByteArray) -> String): Tail {
    val input = asByteArray()
    val output = mapper(input)
    return output.asTail()
}

/**
 * Convenience function for mapping strings.
 * @see asString
 * @see asTail
 * */
@JvmName("mapStringToBytes")
inline fun Tail.mapString(mapper: (String) -> ByteArray): Tail {
    val input = asString()
    val output = mapper(input)
    return output.asTail()
}

/**
 * Convenience function for mapping strings.
 * @see asString
 * @see asTail
 * */
@JvmName("mapStringToString")
inline fun Tail.mapString(mapper: (String) -> String): Tail {
    val input = asString()
    val output = mapper(input)
    return output.asTail()
}