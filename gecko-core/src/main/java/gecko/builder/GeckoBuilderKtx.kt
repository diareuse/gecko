package gecko.builder

import gecko.Gecko

@Deprecated("Add steps via @AutoService annotated class")
inline fun gecko(builder: GeckoConfiguration.() -> Unit = {}): Gecko {
    return Gecko
}