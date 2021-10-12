package com.axkov.moviepick.api

import okhttp3.Interceptor
import okhttp3.Response

class TmdbInterceptor(private val apiKeyProvider: TmdbApiKeyProvider): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        handleIntercept(chain)

    private fun handleIntercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val urlBuilder = request.url.newBuilder()
        urlBuilder.setEncodedQueryParameter(TmdbApi.PARAM_API_KEY, apiKeyProvider.requireApiKey())

        val requestBuilder = request.newBuilder().url(urlBuilder.build())
        val response = chain.proceed(requestBuilder.build())

        if (!response.isSuccessful) {
            // re-try if the server indicates we should
            val retryHeader = response.header("Retry-After")

            if (retryHeader != null) {
                val retry = Integer.parseInt(retryHeader)
                Thread.sleep(((retry + 0.5) * 1000).toLong())
            }

            response.body?.close()

            return handleIntercept(chain)
        }
        return response
    }

}