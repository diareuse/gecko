package gecko.android

import android.content.Context
import gecko.Gecko
import gecko.android.model.asCall
import gecko.android.model.asPersistedRequest
import gecko.android.model.asPersistedResponse
import gecko.model.NetworkMetadata
import gecko.model.Tail

class GeckoPersisted internal constructor(
    private val gecko: Gecko,
    private val database: GeckoDatabase
) : Gecko {

    internal constructor(
        gecko: Gecko,
        factory: GeckoDatabaseFactory
    ) : this(
        gecko,
        factory.getDatabase()
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

    override fun process(metadata: NetworkMetadata): Tail = gecko.process(metadata).also {
        database.runInTransaction {
            val id = call.insert(it.asCall())
            request.insert(metadata.request.asPersistedRequest(id))
            response.insert(metadata.response.asPersistedResponse(id))
        }
    }

}