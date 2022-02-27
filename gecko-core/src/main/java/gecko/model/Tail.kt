package gecko.model

sealed class Tail {

    class Bytes(val value: ByteArray) : Tail()
    class Text(val value: String) : Tail()

}

// ---

fun String.asTail(): Tail = Tail.Text(this)

fun ByteArray.asTail(): Tail = Tail.Bytes(this)

// ---

fun Tail.asByteArray() = when (this) {
    is Tail.Bytes -> value
    is Tail.Text -> value.toByteArray()
}

fun Tail.asString() = when (this) {
    is Tail.Bytes -> value.decodeToString()
    is Tail.Text -> value
}

// ---

@JvmName("mapBytesToBytes")
inline fun Tail.mapBytes(mapper: (ByteArray) -> ByteArray): Tail {
    val input = asByteArray()
    val output = mapper(input)
    return Tail.Bytes(output)
}

@JvmName("mapBytesToString")
inline fun Tail.mapBytes(mapper: (ByteArray) -> String): Tail {
    val input = asByteArray()
    val output = mapper(input)
    return Tail.Text(output)
}

@JvmName("mapStringToBytes")
inline fun Tail.mapString(mapper: (String) -> ByteArray): Tail {
    val input = asString()
    val output = mapper(input)
    return Tail.Bytes(output)
}

@JvmName("mapStringToString")
inline fun Tail.mapString(mapper: (String) -> String): Tail {
    val input = asString()
    val output = mapper(input)
    return Tail.Text(output)
}