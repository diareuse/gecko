package gecko.android

import androidx.annotation.WorkerThread
import gecko.android.model.GeckoData

interface GeckoSource {

    @WorkerThread
    fun getMetadata(id: Long): GeckoData

}