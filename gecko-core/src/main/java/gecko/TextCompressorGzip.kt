package gecko

import com.google.auto.service.AutoService
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

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