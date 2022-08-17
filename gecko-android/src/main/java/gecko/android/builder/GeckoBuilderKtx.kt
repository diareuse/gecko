package gecko.android.builder

import android.content.Context
import gecko.Gecko
import gecko.builder.GeckoConfiguration

@Deprecated("Add steps via @AutoService annotated class")
inline fun Context.geckoAndroid(builder: GeckoConfiguration.() -> Unit = {}): Gecko {
    return Gecko
}