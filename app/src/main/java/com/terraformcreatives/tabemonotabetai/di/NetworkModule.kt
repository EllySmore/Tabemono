package com.terraformcreatives.tabemonotabetai.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.terraformcreatives.tabemonotabetai.BuildConfig
import com.terraformcreatives.tabemonotabetai.network.services.EdamamApiService
import dagger.Module
import retrofit2.Retrofit
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    private val BASE_URL: String = "https://api.edamam.com"

    @Provides
    fun providesConfigs(): Map<String, String> {
        return mapOf(
            Pair("app_id", BuildConfig.API_APP_ID),
            Pair("app_key", BuildConfig.API_KEY)
        )
    }

    @Provides
    fun provideItemService(retrofit: Retrofit): EdamamApiService {
        return retrofit.create(EdamamApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }
}

