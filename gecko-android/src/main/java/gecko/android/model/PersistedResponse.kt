package gecko.android.model

import androidx.room.*
import gecko.android.adapter.HeadersAdapter
import gecko.model.Headers

@TypeConverters(HeadersAdapter::class)
@Entity(
    tableName = "responses",
    foreignKeys = [
        ForeignKey(
            entity = PersistedCall::class,
            parentColumns = ["id"],
            childColumns = ["id_call"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
internal data class PersistedResponse(
    @PrimaryKey
    @ColumnInfo(name = "id_call") val idCall: Long,
    @ColumnInfo(name = "code") val code: Int,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "protocol") val protocol: String,
    @ColumnInfo(name = "headers") val headers: Headers,
    @ColumnInfo(name = "length") val length: Long,
    @ColumnInfo(name = "body") val body: String
)