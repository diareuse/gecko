package gecko.model

import com.google.auto.value.AutoValue
import gecko.interceptor.ByteDataInterceptor
import gecko.model.adapter.asByteData
import gecko.util.loadServices

/**
 * Wraps over [ByteArray] to create decorated steps invoked every time [value] is accessed.
 * */
@AutoValue
abstract class ByteData {

    abstract val value: ByteArray

    companion object {

        fun from(value: String): ByteData {
            return AutoValue_ByteData(value.encodeToByteArray())
        }

        fun from(metadata: NetworkMetadata): ByteData = loadServices<ByteDataInterceptor>()
            .sortedBy { it.stage }
            .fold(metadata.asByteData()) { acc, it -> it.intercept(acc) }

    }

}