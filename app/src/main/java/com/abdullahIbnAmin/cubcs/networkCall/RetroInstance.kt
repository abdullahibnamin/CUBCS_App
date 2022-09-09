package com.abdullahIbnAmin.cubcs.networkCall

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {
        private const val BASEURL = "http://192.168.0.107:8000/"

        fun getRetroInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}





























