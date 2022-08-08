package gecko.android.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.TypeConverters
import gecko.android.adapter.DateConverter
import gecko.android.adapter.HeadersConverter
import gecko.model.Headers
import java.util.*

@TypeConverters(DateConverter::class, HeadersConverter::class)
@DatabaseView(
    viewName = "call",
    value = """
        select 
            calls.id as id,
            calls.link as cl,
            calls.created_at as cc,
            req.method as qm,
            req.url as qu,
            req.headers as qh,
            req.length as ql,
            req.content_type as qt,
            req.body as qb,
            res.code as sc,
            res.message as sm,
            res.protocol as sp,
            res.headers as sh,
            res.length as sl,
            res.content_type as st,
            res.body as sb
        from calls
        left join requests as req on req.id_call=calls.id
        left join responses as res on res.id_call=calls.id
    """
)
internal data class CallView(
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "cl") val link: String,
    @ColumnInfo(name = "cc") val createdAt: Date,
    @ColumnInfo(name = "qm") val requestMethod: String,
    @ColumnInfo(name = "qu") val requestUrl: String,
    @ColumnInfo(name = "qh") val requestHeaders: Headers,
    @ColumnInfo(name = "ql") val requestLength: Long,
    @ColumnInfo(name = "qt") val requestContentType: String,
    @ColumnInfo(name = "qb") val requestBody: String,
    @ColumnInfo(name = "sc") val responseCode: Int,
    @ColumnInfo(name = "sm") val responseMessage: String,
    @ColumnInfo(name = "sp") val responseProtocol: String,
    @ColumnInfo(name = "sh") val responseHeaders: Headers,
    @ColumnInfo(name = "sl") val responseLength: Long,
    @ColumnInfo(name = "st") val responseContentType: String,
    @ColumnInfo(name = "sb") val responseBody: String
)