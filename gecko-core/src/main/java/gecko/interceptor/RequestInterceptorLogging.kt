package gecko.interceptor

import com.google.auto.service.AutoService
import gecko.model.Request
import gecko.model.effect.withLogging

@AutoService(RequestInterceptor::class)
class RequestInterceptorLogging : RequestInterceptor {

    override fun intercept(request: Request): Request {
        return request.withLogging()
    }

}