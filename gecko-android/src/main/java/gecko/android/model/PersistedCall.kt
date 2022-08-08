package gecko.android.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import gecko.android.adapter.DateConverter
import gecko.model.AbstractGeckoModel
import java.util.*

@TypeConverters(DateConverter::class)
@Entity(tableName = "calls")
internal open class PersistedCall(
    @ColumnInfo(name = "link")
    open val link: String = "",
    @ColumnInfo(name = "created_at")
    open val createdAt: Date = Date(),
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    open val id: Long = 0
) : AbstractGeckoModel() {

    override val properties
        get() = sequenceOf(
            ::link,
            ::createdAt,
            ::id
        )

}