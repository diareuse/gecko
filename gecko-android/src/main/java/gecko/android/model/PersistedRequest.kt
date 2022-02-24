package gecko.android.model

import androidx.room.*
import gecko.android.adapter.HeadersAdapter
import gecko.model.Headers

@TypeConverters(HeadersAdapter::class)
@Entity(
    tableName = "requests",
    foreignKeys = [
        ForeignKey(
            entity = PersistedCall::class,
            parentColumns = ["id"],
            childColumns = ["id_call"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
internal data class PersistedRequest(
    @PrimaryKey
    @ColumnInfo(name = "id_call") val idCall: Long,
    @ColumnInfo(name = "method") val method: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "headers") val headers: Headers,
    @ColumnInfo(name = "length") val length: Long,
    @ColumnInfo(name = "body") val body: String
)