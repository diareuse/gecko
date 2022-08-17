package gecko.interceptor

import com.google.auto.service.AutoService
import gecko.model.ByteData
import gecko.model.ByteDataStage
import org.jetbrains.annotations.TestOnly

/** Wraps [ByteData] and returns an url with [domain]. */
@AutoService(ByteDataInterceptor::class)
class ByteDataInterceptorDomainInjecting : ByteDataInterceptor {

    internal var domain: String = "diareuse.github.io/gecko"
        @TestOnly set

    override val stage: ByteDataStage
        get() = ByteDataStage.PostProcessing

    override fun intercept(data: ByteData): ByteData {
        return ByteData.from("https://%s/?q=%s".format(domain, data.value.decodeToString()))
    }

}