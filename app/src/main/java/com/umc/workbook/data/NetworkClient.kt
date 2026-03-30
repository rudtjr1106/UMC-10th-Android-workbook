package com.umc.workbook.data

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    private const val BASE_URL = "https://reqres.in/"
    private const val API_KEY = "reqres_5193c564727d460caf02211006d13c9b" // 해당 키는 제 키라 새로 발급 받으셔서 사용하세요!

    class AuthenticationInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", API_KEY)
                .build()

            Log.d(
                "RETROFIT",
                "AuthenticationInterceptor - intercept() called / request header: ${request.headers}"
            )
            return chain.proceed(request)
        }
    }

    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            when {
                !message.isJsonObject() && !message.isJsonArray() ->
                    Log.d("RETROFIT", "CONNECTION INFO -> $message")
                else ->
                    try {
                        Log.d(
                            "RETROFIT",
                            GsonBuilder().setPrettyPrinting().create().toJson(
                                JsonParser.parseString(message)
                            )
                        )
                    } catch (_: JsonSyntaxException) {
                        Log.d("RETROFIT", message)
                    }
            }
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthenticationInterceptor())
        .addInterceptor(provideLoggingInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val reqResApiService: ReqResApiService by lazy {
        retrofit.create(ReqResApiService::class.java)
    }
}

private fun String.isJsonObject(): Boolean = startsWith("{") && endsWith("}")

private fun String.isJsonArray(): Boolean = startsWith("[") && endsWith("]")
