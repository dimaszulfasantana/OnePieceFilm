package com.dimas.onepiecefilm.core.di

import androidx.room.Room
import com.dimas.onepiecefilm.core.source.OnePieceRepository
import com.dimas.onepiecefilm.core.source.local.LocalDataSource
import com.dimas.onepiecefilm.core.source.local.OnePieceDatabase
import com.dimas.onepiecefilm.core.source.remote.ApiService
import com.dimas.onepiecefilm.core.source.remote.RemoteDataSource
import com.dimas.onepiecefilm.core.domain.repository.IOnePieceRepository
import com.dimas.onepiecefilm.core.utils.Constant.BASE_URL
import com.dimas.onepiecefilm.core.utils.Constant.DATABASE_NAME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<OnePieceDatabase>().onePieceDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            OnePieceDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IOnePieceRepository> { OnePieceRepository(get(), get()) }
}

