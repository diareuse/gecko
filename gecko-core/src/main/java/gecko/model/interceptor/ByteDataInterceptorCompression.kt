package gecko.model.interceptor

import com.google.auto.service.AutoService
import gecko.model.ByteData
import gecko.model.ByteDataStage
import gecko.model.decorator.asCompressed

/** Wraps [ByteData] and returns compressed counterpart. */
@AutoService(ByteDataInterceptor::class)
class ByteDataInterceptorCompression : ByteDataInterceptor {

    override val stage: ByteDataStage
        get() = ByteDataStage.Compression

    override fun intercept(data: ByteData): ByteData {
        return data.asCompressed()
    }

}