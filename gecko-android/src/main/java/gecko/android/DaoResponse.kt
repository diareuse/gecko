package gecko.android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import gecko.android.model.PersistedResponse

@Dao
internal interface DaoResponse {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(model: PersistedResponse)

}