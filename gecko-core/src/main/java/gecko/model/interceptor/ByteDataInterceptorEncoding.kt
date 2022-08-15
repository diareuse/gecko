package gecko.model.interceptor

import com.google.auto.service.AutoService
import gecko.model.ByteData
import gecko.model.ByteDataStage
import gecko.model.decorator.asBase64

@AutoService(ByteDataInterceptor::class)
class ByteDataInterceptorEncoding : ByteDataInterceptor {

    override val stage: ByteDataStage
        get() = ByteDataStage.Encoding

    override fun intercept(data: ByteData): ByteData {
        return data.asBase64()
    }

}
