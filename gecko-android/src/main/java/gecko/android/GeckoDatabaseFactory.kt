package gecko.android

import android.content.Context
import gecko.android.adapter.CallAdapter
import gecko.android.adapter.MetadataAdapter
import gecko.android.adapter.RequestAdapter
import gecko.android.adapter.ResponseAdapter

internal interface GeckoDatabaseFactory {

    fun getDatabase(): GeckoDatabase
    fun getCall(): CallAdapter
    fun getRequest(): RequestAdapter
    fun getResponse(): ResponseAdapter
    fun getMetadata(): MetadataAdapter

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