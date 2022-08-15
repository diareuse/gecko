package gecko.android

import android.util.Base64
import com.google.auto.service.AutoService
import gecko.Base64Encoder

@AutoService(Base64Encoder::class)
class Base64EncoderAndroid : Base64Encoder {

    override fun encode(data: ByteArray): ByteArray {
        return Base64.encode(data, Base64.NO_WRAP or Base64.URL_SAFE)
    }

}