package com.huytq.baseproject.di

import android.content.Context
import com.huytq.baseproject.BuildConfig
import com.huytq.baseproject.base.utils.SharedPreferenceUtils
import com.huytq.baseproject.data.remote.api.ApiService
import com.huytq.baseproject.data.repositoryimpl.SampleRepositoryImpl
import com.huytq.baseproject.domain.repository.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val TIME_OUT= 10L

    @Provides
    @Singleton
    fun provideApiService(@ApplicationContext appContext: Context): ApiService {
        val loggingLevel= if(BuildConfig.DEBUG){
            HttpLoggingInterceptor.Level.BODY
        }else{
            HttpLoggingInterceptor.Level.NONE
        }
        val httpLogging = HttpLoggingInterceptor().apply {
            level= loggingLevel
        }
        val token = SharedPreferenceUtils.getInstance(appContext)?.getStringValue("token")
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(httpLogging)
            .addInterceptor {
                val newRequest = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                return@addInterceptor it.proceed(newRequest)
            }
            .build()
        val retrofit= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://google.com.vn")
            .build()
        return  retrofit.create(ApiService::class.java)
    }

}