package gecko.okhttp

import gecko.Gecko
import gecko.model.NetworkMetadata
import okhttp3.Interceptor
import org.jetbrains.annotations.TestOnly

class GeckoInterceptor : Interceptor {

    @Deprecated("Use GeckoInterceptor() instead. It no longer requires explicit initialization")
    constructor(gecko: Gecko) : this() {
        this.gecko = gecko
    }

    constructor()

    internal var gecko: Gecko = Gecko
        @TestOnly set

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val geckoRequest = request.asRequest()
        val response = chain.proceed(request)
        val geckoResponse = response.asResponse()
        val metadata = NetworkMetadata(geckoRequest, geckoResponse)

        gecko.process(metadata)

        return response
    }

    companion object {

        internal val emptyByteArray = ByteArray(0)

    }

}
