package gecko.android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gecko.android.model.CallMetadataView
import gecko.android.model.CallView
import gecko.android.model.PersistedCall

@Dao
internal interface DaoCall {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(model: PersistedCall): Long

    @Query("select * from call_metadata order by call_metadata.cc desc limit :limit offset :offset")
    fun select(offset: Int, limit: Int): List<CallMetadataView>

    @Query("select * from call where call.id=:id limit 1")
    fun select(id: Long): CallView?

}