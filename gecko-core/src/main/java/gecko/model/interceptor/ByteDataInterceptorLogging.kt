package gecko.model.interceptor

import com.google.auto.service.AutoService
import gecko.Logger
import gecko.model.ByteData
import gecko.model.ByteDataStage
import org.jetbrains.annotations.TestOnly

/** Logs [ByteData] and returns the input parameter intact. */
@AutoService(ByteDataInterceptor::class)
class ByteDataInterceptorLogging : ByteDataInterceptor {

    override val stage: ByteDataStage
        get() = ByteDataStage.Finalizing

    internal var logger: Logger = Logger
        @TestOnly set

    override fun intercept(data: ByteData): ByteData {
        return data.also {
            logger.log(data.value.decodeToString())
        }
    }

}