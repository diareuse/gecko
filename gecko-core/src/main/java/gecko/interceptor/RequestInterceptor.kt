package gecko.interceptor

import gecko.model.Request

interface RequestInterceptor {

    fun intercept(request: Request): Request

}