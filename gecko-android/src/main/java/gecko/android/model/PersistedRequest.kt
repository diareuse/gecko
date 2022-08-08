package gecko.android.model

import androidx.room.*
import gecko.android.adapter.HeadersConverter
import gecko.model.AbstractGeckoModel
import gecko.model.Headers

@TypeConverters(HeadersConverter::class)
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
internal open class PersistedRequest(
    @PrimaryKey
    @ColumnInfo(name = "id_call")
    open val idCall: Long = 0,

    @ColumnInfo(name = "method")
    open val method: String = "",

    @ColumnInfo(name = "url")
    open val url: String = "",

    @ColumnInfo(name = "headers")
    open val headers: Headers = emptySet(),

    @ColumnInfo(name = "length")
    open val length: Long = 0,

    @ColumnInfo(name = "content_type")
    open val contentType: String = "",

    @ColumnInfo(name = "body")
    open val body: String = ""
) : AbstractGeckoModel() {

    override val properties
        get() = sequenceOf(
            ::idCall,
            ::method,
            ::url,
            ::headers,
            ::length,
            ::contentType,
            ::body
        )

}