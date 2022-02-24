package gecko.android

import gecko.android.adapter.CallAdapter
import gecko.android.adapter.MetadataAdapter
import gecko.android.adapter.RequestAdapter
import gecko.android.adapter.ResponseAdapter

internal class GeckoDatabaseFactorySingle(
    private val factory: GeckoDatabaseFactory
) : GeckoDatabaseFactory {

    private var database: GeckoDatabase? = null
    private var call: CallAdapter? = null
    private var request: RequestAdapter? = null
    private var response: ResponseAdapter? = null
    private var metadata: MetadataAdapter? = null

    override fun getDatabase() = database ?: synchronized(this) {
        database ?: factory.getDatabase().also { database = it }
    }

    override fun getCall() = call ?: synchronized(this) {
        call ?: factory.getCall().also { call = it }
    }

    override fun getRequest() = request ?: synchronized(this) {
        request ?: factory.getRequest().also { request = it }
    }

    override fun getResponse() = response ?: synchronized(this) {
        response ?: factory.getResponse().also { response = it }
    }

    override fun getMetadata() = metadata ?: synchronized(this) {
        metadata ?: factory.getMetadata().also { metadata = it }
    }

}