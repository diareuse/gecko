package gecko.model

import kotlin.reflect.KProperty0

/**
 * Base class for all models. This model allows for a robust adaptation. It collected [properties]
 * from its derivatives and computes equals, hash codes and string values.
 *
 * It does so superficially, though, as it does not inspect elements in a deep fashion. Meaning, if
 * presented with a Array property, it will **not** compute content hash code for equality purposes.
 * */
abstract class AbstractGeckoModel {

    protected abstract val properties: Sequence<KProperty0<Any?>>

    private val propertyValues
        get() = properties.map { it.get() }

    private val propertyDescription
        get() = properties.joinToString {
            "%s=%s".format(
                it.name,
                it.get().toString()
            )
        }

    override fun toString(): String {
        return "%s(%s)".format(this::class.java.simpleName, propertyDescription)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AbstractGeckoModel) return false
        val thisIterator = propertyValues.iterator()
        val theirIterator = other.propertyValues.iterator()
        while (thisIterator.hasNext() || theirIterator.hasNext()) {
            val thisValue = if (thisIterator.hasNext()) thisIterator.next() else null
            val theirValue = if (theirIterator.hasNext()) theirIterator.next() else null
            if (thisValue != theirValue) {
                return false
            }
        }
        return true
    }

    override fun hashCode(): Int {
        return propertyValues.fold(0) { acc, it ->
            acc * 31 + it.hashCode()
        }
    }

}