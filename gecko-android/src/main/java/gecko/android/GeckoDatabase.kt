package gecko.android

import androidx.room.Database
import androidx.room.RoomDatabase
import gecko.android.model.*

@Database(
    entities = [
        PersistedCall::class,
        PersistedRequest::class,
        PersistedResponse::class
    ],
    views = [
        CallView::class,
        CallMetadataView::class
    ],
    exportSchema = false,
    version = 2
)
internal abstract class GeckoDatabase : RoomDatabase() {

    abstract fun call(): DaoCall
    abstract fun request(): DaoRequest
    abstract fun response(): DaoResponse

}