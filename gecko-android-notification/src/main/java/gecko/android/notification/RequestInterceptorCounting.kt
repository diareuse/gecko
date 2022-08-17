package gecko.android.notification

import com.google.auto.service.AutoService
import gecko.interceptor.RequestInterceptor
import gecko.model.Request

@AutoService(RequestInterceptor::class)
class RequestInterceptorCounting : RequestInterceptor {

    override fun intercept(request: Request): Request {
        return request.also {
            NotificationController.outgoing("→ ${request.method} | ${request.url}")
        }
    }

}