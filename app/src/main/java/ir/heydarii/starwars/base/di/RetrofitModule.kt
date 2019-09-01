package ir.heydarii.starwars.base.di

import dagger.Module
import dagger.Provides
import ir.heydarii.starwars.base.Consts
import ir.heydarii.starwars.retrofit.RetrofitMainInterface
import ir.heydarii.starwars.retrofit.RetrofitServiceGenerator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class RetrofitModule {


    @Singleton
    @Provides
    fun provideOkHttp(interceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        val httpClient = OkHttpClient().newBuilder()
        httpClient.connectTimeout(15, TimeUnit.SECONDS)
        httpClient.readTimeout(15, TimeUnit.SECONDS)
        httpClient.callTimeout(15, TimeUnit.SECONDS)
        httpClient.addInterceptor(interceptor)
        return httpClient
    }

    @Singleton
    @Provides
    fun provedHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    @Named("baseURL")
    fun provideBaseURL(): String {
        return Consts.BASE_URL
    }

    @Singleton
    @Provides
    fun provideMainInterface(retrofit: Retrofit): RetrofitMainInterface {
        return retrofit.create(RetrofitMainInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        converterFactory: GsonConverterFactory,
        httpClient: OkHttpClient.Builder,
        @Named("baseURL")
        baseURL: String
    ): Retrofit {
        return RetrofitServiceGenerator(converterFactory, httpClient, baseURL).getClient()
    }

}