@file:JvmName("Expects")

package android.util

import kotlin.Pair

private var expectedValues: List<Expected> = emptyList()

data class Expected(
    val tag: String,
    val message: String
)

fun unexpect() {
    expectedValues = emptyList()
}

fun expect(tag: String, message: String) {
    expectedValues = listOf(Expected(tag, message))
}

fun expect(vararg values: Pair<String, String>) {
    expectedValues = values.map { Expected(it.first, it.second) }
}

fun assertEquals(tag: String, message: String) {
    val value = Expected(tag, message)
    if (value !in expectedValues) {
        throw IllegalArgumentException("Expected any in $expectedValues, instead got $value")
    }
}