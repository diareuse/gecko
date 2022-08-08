package gecko.android.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.TypeConverters
import gecko.android.adapter.DateConverter
import java.util.*

@TypeConverters(DateConverter::class)
@DatabaseView(
    viewName = "call_metadata",
    value = """
        select 
            calls.id as id,
            calls.created_at as cc,
            req.method as qm,
            req.url as qu,
            req.length as ql,
            res.code as sc,
            res.length as sl
        from calls
        left join requests as req on req.id_call=calls.id
        left join responses as res on res.id_call=calls.id
    """
)
internal data class CallMetadataView(
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "cc") val createdAt: Date,
    @ColumnInfo(name = "qm") val requestMethod: String,
    @ColumnInfo(name = "qu") val requestUrl: String,
    @ColumnInfo(name = "ql") val requestLength: Long,
    @ColumnInfo(name = "sc") val responseCode: Int,
    @ColumnInfo(name = "sl") val responseLength: Long,
)