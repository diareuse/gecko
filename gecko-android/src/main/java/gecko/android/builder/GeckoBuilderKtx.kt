@file:Suppress("DEPRECATION")

package gecko.android.builder

import android.content.Context
import gecko.Gecko
import gecko.builder.GeckoConfiguration

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Add steps via @AutoService annotated class")
inline fun Context.geckoAndroid(builder: GeckoConfiguration.() -> Unit = {}): Gecko {
    return Gecko
}