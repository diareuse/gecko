package gecko.android.notification

import com.google.auto.service.AutoService
import gecko.interceptor.ResponseInterceptor
import gecko.model.Response

@AutoService(ResponseInterceptor::class)
class ResponseInterceptorCounting : ResponseInterceptor {

    override fun intercept(response: Response): Response {
        return response.also {
            NotificationController.incoming("← ${response.code} | ${response.length} bytes")
        }
    }

}