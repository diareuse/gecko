package gecko.android

import android.content.Context

interface GeckoSourceFactory {

    fun getInstance(): GeckoSource

    companion object {

        private var instance: GeckoSourceFactory? = null

        @Suppress("ComplexRedundantLet")
        fun getInstance(context: Context) = instance ?: synchronized(GeckoSourceFactory) {
            instance ?: GeckoSourceFactoryDefault(GeckoDatabaseFactory.getInstance(context))
                .let { GeckoSourceFactorySingle(it) }
                .also { instance = it }
        }

    }

}