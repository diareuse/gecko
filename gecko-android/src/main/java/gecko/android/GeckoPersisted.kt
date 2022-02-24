package gecko.android

import android.content.Context
import gecko.Gecko
import gecko.android.adapter.CallAdapter
import gecko.android.adapter.RequestAdapter
import gecko.android.adapter.ResponseAdapter
import gecko.model.NetworkMetadata

class GeckoPersisted internal constructor(
    private val gecko: Gecko,
    private val database: GeckoDatabase,
    private val adapterCall: CallAdapter,
    private val adapterRequest: RequestAdapter,
    private val adapterResponse: ResponseAdapter
) : Gecko {

    internal constructor(
        gecko: Gecko,
        factory: GeckoDatabaseFactory
    ) : this(
        gecko,
        factory.getDatabase(),
        factory.getCall(),
        factory.getRequest(),
        factory.getResponse()
    )

    constructor(
        gecko: Gecko,
        context: Context
    ) : this(
        gecko,
        GeckoDatabaseFactory.getInstance(context)
    )

    private val call by lazy { database.call() }
    private val request by lazy { database.request() }
    private val response by lazy { database.response() }

    override fun process(metadata: NetworkMetadata) = gecko.process(metadata).also {
        database.runInTransaction {
            val id = call.insert(adapterCall.adapt(it))
            request.insert(adapterRequest.adapt(id, metadata.request))
            response.insert(adapterResponse.adapt(id, metadata.response))
        }
    }

}