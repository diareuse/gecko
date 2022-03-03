package gecko.android.adapter

import androidx.room.TypeConverter
import java.util.*

internal class DateAdapter {

    @TypeConverter
    fun toDatabase(date: Date) = date.time

    @TypeConverter
    fun fromDatabase(date: Long) = Date(date)

}