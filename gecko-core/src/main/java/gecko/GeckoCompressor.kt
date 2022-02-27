package gecko

import gecko.model.NetworkMetadata
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

class GeckoCompressor(
    private val source: Gecko
) : Gecko {

    override fun process(metadata: NetworkMetadata): ByteArray {
        val output = source.process(metadata)
        val compressed = ByteArrayOutputStream(output.size).use { byteOutput ->
            GZIPOutputStream(byteOutput).use { gzipOutput ->
                gzipOutput.write(output)
                gzipOutput.finish()
            }
            byteOutput.toByteArray()
        }
        return compressed
    }

}