package gecko.android

import android.util.Log
import com.google.auto.service.AutoService
import gecko.Logger

@AutoService(Logger::class)
class LoggerAndroid : Logger {

    override fun log(message: String) {
        message.asMessageSequence().forEach {
            Log.v("Gecko!", it)
        }
    }

    private fun String.asMessageSequence(lineLength: Int = 4096) = sequence {
        var offset = 0
        while (offset < length) {
            val start = offset
            val end = (offset + lineLength).coerceAtMost(length)
            val string = slice(start until end)

            offset += string.length

            yield(string)
        }
    }

}