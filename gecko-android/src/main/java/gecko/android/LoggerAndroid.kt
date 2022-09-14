package gecko.android

import android.util.Log
import com.google.auto.service.AutoService
import gecko.Logger

@AutoService(Logger::class)
class LoggerAndroid : Logger {

    override fun log(message: String) {
        message.chunkedSequence(4000).forEach {
            Log.v("Gecko!", it)
        }
    }

}