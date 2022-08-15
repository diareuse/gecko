package gecko.okhttp

import gecko.Gecko
import gecko.model.NetworkMetadata
import okhttp3.Interceptor

class GeckoInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val geckoRequest = request.asRequest()
        val response = chain.proceed(request)
        val geckoResponse = response.asResponse()
        val metadata = NetworkMetadata(geckoRequest, geckoResponse)

        Gecko.process(metadata)

        return response
    }

    companion object {

        internal val emptyByteArray = ByteArray(0)

    }

}
