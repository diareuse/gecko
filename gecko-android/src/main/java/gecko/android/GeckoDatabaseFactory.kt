package gecko.android

import android.content.Context

internal interface GeckoDatabaseFactory {

    fun getDatabase(): GeckoDatabase

    companion object {

        private var instance: GeckoDatabaseFactory? = null

        fun initialize(context: Context) {
            instance = GeckoDatabaseFactoryDefault(context)
        }

        fun getInstance() = requireNotNull(instance) {
            "Did you call initialize(Context)?"
        }

    }

}