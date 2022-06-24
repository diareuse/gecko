package gecko.builder

import gecko.Base64EncoderJava
import gecko.Gecko
import gecko.Logger
import gecko.MetadataAdapterAscii

inline fun gecko(builder: GeckoConfiguration.() -> Unit = {}): Gecko {
    return GeckoBuilder().apply(::geckoDefaults).apply(builder).build()
}

@PublishedApi
internal fun geckoDefaults(configuration: GeckoConfiguration) {
    configuration.domain = "diareuse.github.io/gecko"
    configuration.encoder = Base64EncoderJava()
    configuration.adapter = MetadataAdapterAscii()
    configuration.logger = Logger { println(it) }
}
