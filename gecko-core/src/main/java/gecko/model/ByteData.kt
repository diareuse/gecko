package gecko.model

import com.google.auto.value.AutoValue
import gecko.model.adapter.asByteData
import gecko.model.interceptor.ByteDataInterceptor
import gecko.util.loadServices

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