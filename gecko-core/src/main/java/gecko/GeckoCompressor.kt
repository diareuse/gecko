package gecko

import gecko.model.NetworkMetadata
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

class GeckoCompressor(
    private val source: Gecko
) : Gecko {

    override fun process(metadata: NetworkMetadata): ByteArray {
        val output = source.process(metadata)
        val byteOutput = ByteArrayOutputStream()
        val gzipOutput = GZIPOutputStream(byteOutput, true)
        gzipOutput.write(output)
        gzipOutput.flush()
        return byteOutput.toByteArray()
    }

}