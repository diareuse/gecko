package gecko.builder

import gecko.Base64Encoder
import gecko.Gecko
import gecko.Logger
import gecko.MetadataAdapterAscii
import java.util.*

inline fun gecko(builder: GeckoConfiguration.() -> Unit = {}): Gecko {
    return GeckoBuilder().apply(builder).apply(::geckoDefaults).build()
}

@PublishedApi
internal fun geckoDefaults(configuration: GeckoConfiguration) {
    configuration.domain = "diareuse.github.io/gecko"
    configuration.encoder = object : Base64Encoder {
        val encoder = Base64.getUrlEncoder()
        override fun encode(data: ByteArray): ByteArray {
            return encoder.encode(data)
        }
    }
    configuration.adapter = MetadataAdapterAscii()
    configuration.logger = Logger { println(it) }
}
