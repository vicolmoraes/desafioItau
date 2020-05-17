package com.vitoria.desafioitau.data

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitConfig {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://desafio-it-server.herokuapp.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    fun buildingService(): Services {
        return retrofit.create(Services::class.java)
    }
}