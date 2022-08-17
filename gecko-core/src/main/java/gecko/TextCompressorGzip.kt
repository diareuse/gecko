package gecko

import com.google.auto.service.AutoService
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

/**
 * Class uses a GZIP from standard Java util to compress and minify data. It loads all the [TextCompressor.compress]
 * parameters to memory when compressing. This may be unsuitable for large amounts of data, such as videos or images.
 * @see GZIPOutputStream
 * */
@AutoService(TextCompressor::class)
class TextCompressorGzip : TextCompressor {

    override fun compress(data: ByteArray): ByteArray = ByteArrayOutputStream(data.size).use { byteOutput ->
        GZIPOutputStream(byteOutput).use { gzipOutput ->
            gzipOutput.write(data)
            gzipOutput.finish()
        }
        byteOutput.toByteArray()
    }

}