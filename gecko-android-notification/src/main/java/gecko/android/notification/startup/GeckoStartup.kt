package gecko.android.notification.startup

import android.content.Context
import androidx.startup.Initializer
import java.lang.ref.WeakReference

class GeckoStartup : Initializer<Unit> {

    override fun create(context: Context) {
        contextInternal = WeakReference(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    companion object {

        private lateinit var contextInternal: WeakReference<Context>

        internal val context: Context
            get() = contextInternal.get().let(::requireNotNull)

    }

}