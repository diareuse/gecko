package gecko.android.builder

import android.content.Context
import android.util.Base64
import android.util.Log
import gecko.Base64Encoder
import gecko.Gecko
import gecko.Logger
import gecko.android.GeckoPersisted
import gecko.builder.GeckoConfiguration
import gecko.builder.gecko

inline fun Context.geckoAndroid(builder: GeckoConfiguration.() -> Unit = {}): Gecko {
    return gecko {
        addStep { GeckoPersisted(it, applicationContext) }
        geckoAndroidDefaults(this)
        builder(this)
    }
}

@PublishedApi
internal fun geckoAndroidDefaults(configuration: GeckoConfiguration) {
    configuration.encoder = Base64Encoder { Base64.encode(it, Base64.NO_WRAP or Base64.URL_SAFE) }
    configuration.logger = Logger {
        var offset = 0
        while (offset < it.length) {
            val string = it.slice(offset until (offset + 4096).coerceAtMost(it.length))
            offset += string.length
            Log.v("Gecko!", string)
        }
    }
}