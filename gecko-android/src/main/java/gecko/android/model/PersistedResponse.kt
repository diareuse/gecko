package gecko.android.model

import androidx.room.*
import gecko.android.adapter.HeadersConverter
import gecko.model.AbstractGeckoModel
import gecko.model.Headers

@TypeConverters(HeadersConverter::class)
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
internal open class PersistedResponse(
    @PrimaryKey
    @ColumnInfo(name = "id_call")
    open val idCall: Long = 0,

    @ColumnInfo(name = "code")
    open val code: Int = 0,

    @ColumnInfo(name = "message")
    open val message: String = "",

    @ColumnInfo(name = "protocol")
    open val protocol: String = "",

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
            ::code,
            ::message,
            ::protocol,
            ::headers,
            ::length,
            ::contentType,
            ::body
        )

}