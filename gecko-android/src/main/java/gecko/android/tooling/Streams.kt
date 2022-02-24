package gecko.android.tooling

import gecko.model.Body
import java.io.ByteArrayOutputStream

fun Body.readToString(): String {
    val output = ByteArrayOutputStream()
    output.use(::invoke)
    return output.toString()
}