package gecko.model.interceptor

import gecko.model.ByteData
import gecko.model.ByteDataStage

/**
 * Intercepts [ByteData] in specified [stage]. Consumer can do whatever with the provided data and then return
 * the result of the operation.
 * */
interface ByteDataInterceptor {

    /** @see ByteDataStage */
    val stage: ByteDataStage

    /** Updates, processes or adjusts [data] provided, returns result of the operation. */
    fun intercept(data: ByteData): ByteData

}