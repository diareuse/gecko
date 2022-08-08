package gecko.android

import android.content.Context

internal interface GeckoDatabaseFactory {

    fun getDatabase(): GeckoDatabase

    companion object {

        private var instance: GeckoDatabaseFactory? = null

        @Suppress("ComplexRedundantLet")
        fun getInstance(context: Context) = instance ?: synchronized(GeckoDatabaseFactory) {
            instance ?: GeckoDatabaseFactoryDefault(context)
                .let { GeckoDatabaseFactorySingle(it) }
                .also { instance = it }
        }

    }

}