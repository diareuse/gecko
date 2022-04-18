package gecko

import gecko.model.NetworkMetadata
import gecko.model.Tail
import gecko.model.mapBytes
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

/**
 * Uses previous step's result to compress a given result.
 * */
class GeckoCompressor(
    private val source: Gecko
) : Gecko {

    override fun process(metadata: NetworkMetadata): Tail = source.process(metadata)
        .mapBytes(::compress)

    private fun compress(bytes: ByteArray) = ByteArrayOutputStream(bytes.size).use { byteOutput ->
        GZIPOutputStream(byteOutput).use { gzipOutput ->
            gzipOutput.write(bytes)
            gzipOutput.finish()
        }
        byteOutput.toByteArray()
    }

}