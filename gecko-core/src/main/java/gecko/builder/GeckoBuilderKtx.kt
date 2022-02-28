package gecko.builder

import gecko.Base64Encoder
import gecko.Gecko
import gecko.Logger
import gecko.MetadataAdapterAscii
import java.util.*

inline fun gecko(builder: GeckoConfiguration.() -> Unit = {}): Gecko {
    return GeckoBuilder().apply(::geckoDefaults).apply(builder).build()
}

@PublishedApi
internal fun geckoDefaults(configuration: GeckoConfiguration) {
    val encoder = Base64.getUrlEncoder()
    configuration.domain = "diareuse.github.io/gecko"
    configuration.encoder = Base64Encoder { encoder.encode(it) }
    configuration.adapter = MetadataAdapterAscii()
    configuration.logger = Logger { println(it) }
}
