package com.example.recipegenerator.network

import com.example.recipegenerator.ResultResponse
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class ApiClient {
    private companion object {
        private const val BASE_URL = "https://app.rakuten.co.jp/services/api/"
    }

    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val apiService : ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }

    suspend fun fetchResults(): Response<ResultResponse> {
        return apiService.fetchResults(getAPIKey())
    }
}

interface ApiService {
    @GET("Recipe/CategoryRanking/20170426")
    suspend fun fetchResults(
        @Query("applicationId") applicationId: String,
        @Query("categoryId") categoryId: Int = 30,
    ): Response<ResultResponse>
}