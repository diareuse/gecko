package gecko.model.interceptor

import gecko.model.ByteData
import gecko.model.ByteDataStage

interface ByteDataInterceptor {

    val stage: ByteDataStage
    fun intercept(data: ByteData): ByteData

}