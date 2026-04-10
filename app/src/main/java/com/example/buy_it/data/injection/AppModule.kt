package com.example.buy_it.data.injection

import com.example.buy_it.data.datasource.services.UserRetrofitService
import com.example.buy_it.data.datasource.services.ProductRetrofitService
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesUserRetrofitService(retrofit: Retrofit): UserRetrofitService{
        return retrofit.create(UserRetrofitService::class.java)
    fun providesProductRetrofitService(retrofit: Retrofit): ProductRetrofitService {
        return retrofit.create(ProductRetrofitService::class.java)
    }
}