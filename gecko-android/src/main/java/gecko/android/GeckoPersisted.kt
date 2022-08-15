package gecko.android

import com.google.auto.service.AutoService
import gecko.Gecko
import gecko.GeckoThreadSwitching
import gecko.android.model.snapshotRequest
import gecko.android.model.snapshotResponse
import gecko.android.model.toCall
import gecko.model.ByteData
import gecko.model.NetworkMetadata

@AutoService(Gecko::class)
class GeckoPersisted : GeckoThreadSwitching() {

    private val database by lazy { GeckoDatabaseFactory.getInstance().getDatabase() }
    private val call by lazy { database.call() }
    private val request by lazy { database.request() }
    private val response by lazy { database.response() }

    override fun processAsync(metadata: NetworkMetadata) {
        database.runInTransaction {
            val id = call.insert(ByteData.from(metadata).toCall())
            request.insert(metadata.request.snapshotRequest(id))
            response.insert(metadata.response.snapshotResponse(id))
        }
    }

}