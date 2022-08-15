package gecko.android

import android.content.Context
import androidx.startup.Initializer
import gecko.Gecko

class GeckoStartup : Initializer<Gecko> {

    override fun create(context: Context): Gecko {
        GeckoDatabaseFactory.initialize(context)
        return Gecko
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}