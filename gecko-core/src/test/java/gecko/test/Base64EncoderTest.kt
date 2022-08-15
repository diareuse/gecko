package gecko.test

import com.google.auto.service.AutoService
import gecko.Base64Encoder

@AutoService(Base64Encoder::class)
class Base64EncoderTest : Base64Encoder {
    override fun encode(data: ByteArray): ByteArray {
        return data
    }
}