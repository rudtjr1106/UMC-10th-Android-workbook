package com.umc.workbook.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.umc.workbook.data.ReqResApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://reqres.in/"
    private const val API_KEY = "reqres_5193c564727d460caf02211006d13c9b"

    private class AuthenticationInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", API_KEY)
                .build()
            return chain.proceed(request)
        }
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            when {
                !message.isJsonObject() && !message.isJsonArray() ->
                    Log.d("RETROFIT", "CONNECTION INFO -> $message")

                else ->
                    try {
                        Log.d(
                            "RETROFIT",
                            GsonBuilder().setPrettyPrinting().create()
                                .toJson(JsonParser.parseString(message))
                        )
                    } catch (_: JsonSyntaxException) {
                        Log.d("RETROFIT", message)
                    }
            }
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthenticationInterceptor())
            .addInterceptor(provideLoggingInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideReqResApiService(retrofit: Retrofit): ReqResApiService {
        return retrofit.create(ReqResApiService::class.java)
    }
}

private fun String.isJsonObject(): Boolean = startsWith("{") && endsWith("}")
private fun String.isJsonArray(): Boolean = startsWith("[") && endsWith("]")
