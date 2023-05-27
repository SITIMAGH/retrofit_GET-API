package com.example.retrofit_mod8

import retrofit2.Call
import retrofit2.http.GET

interface APIServices {
    @GET("datamahasiswa/")
    fun getdatamahasiswa():Call<APIrespons>
}