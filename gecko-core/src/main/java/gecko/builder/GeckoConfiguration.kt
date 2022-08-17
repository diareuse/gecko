package gecko.builder

import gecko.Base64Encoder
import gecko.Gecko
import gecko.Logger

@Deprecated("Configure using @AutoService")
interface GeckoConfiguration {

    @Deprecated("Configure using @AutoService")
    var domain: String

    @Deprecated("Configure using @AutoService")
    var encoder: Base64Encoder

    @Deprecated("Configure using @AutoService")
    var adapter: Any

    @Deprecated("Configure using @AutoService")
    var logger: Logger

    @Deprecated("Configure using @AutoService")
    fun addStep(wrapper: (Gecko) -> Gecko)

}