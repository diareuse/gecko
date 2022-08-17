package gecko.interceptor

import gecko.model.Response

interface ResponseInterceptor {

    fun intercept(response: Response): Response

}