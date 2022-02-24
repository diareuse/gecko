package gecko.android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import gecko.android.model.PersistedRequest

@Dao
internal interface DaoRequest {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(model: PersistedRequest)

}
