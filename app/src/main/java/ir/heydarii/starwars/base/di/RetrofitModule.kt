package ir.heydarii.starwars.base.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.heydarii.starwars.repository.network.RetrofitMainInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val BASE_URL = "https://swapi.dev/api/"

/**
 * Dagger module to provide Retrofit necessary objects
 */
@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    /**
     * Provides and configs okHttp
     */
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

    /**
     * Provides and configs logger to see the logs in terminal
     */
    @Singleton
    @Provides
    fun provedHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * Provides GsonConvertor
     */
    @Singleton
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    /**
     * Provides the retrofit interface
     */
    @Singleton
    @Provides
    fun provideMainInterface(retrofit: Retrofit): RetrofitMainInterface {
        return retrofit.create(RetrofitMainInterface::class.java)
    }

    /**
     * Provides the retrofit object
     */
    @Singleton
    @Provides
    fun provideRetrofit(
        converterFactory: MoshiConverterFactory,
        httpClient: OkHttpClient.Builder,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }
}
