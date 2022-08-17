package gecko.interceptor

import com.google.auto.service.AutoService
import gecko.model.Response
import gecko.model.effect.withLogging

@AutoService(ResponseInterceptor::class)
class ResponseInterceptorLogging : ResponseInterceptor {

    override fun intercept(response: Response): Response {
        return response.withLogging()
    }

}