package gecko.android.adapter

import androidx.room.TypeConverter
import gecko.model.Headers

internal class HeadersConverter {

    @TypeConverter
    fun toDatabase(headers: Headers) =
        headers.joinToString(separator = "|") { "${it.first}:${it.second}" }

    @TypeConverter
    fun fromDatabase(headers: String): Headers = headers.splitToSequence("|")
        .map { it.split(':') }
        .map { (first, second) -> first to second }
        .toList()

}