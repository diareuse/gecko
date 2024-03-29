package gecko.android.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import gecko.android.adapter.DateConverter
import java.util.*

@TypeConverters(DateConverter::class)
@Entity(tableName = "calls")
data class PersistedCall(
    @ColumnInfo(name = "link")
    val link: String = "",

    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0
)
