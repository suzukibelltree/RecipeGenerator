package com.example.recipegenerator.network

import com.example.recipegenerator.DataClass.RankingRecipeData
import com.example.recipegenerator.DataClass.Result
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
        //private const val APPLICATION_ID = "ここにApiキーを入れる"
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
        return apiService.fetchResults(ApiKey().getApiKey())
    }

    //h_ryoが使う関数/カテゴリ取得
    suspend fun fetchRecipeCategory(categoryType: String): Response<Result> {
        return apiService.fetchRecipeCategory(ApiKey().categoryApiKey(), categoryType)
    }

    //h_ryoが使う関数/ランキング取得
    suspend fun fetchRecipeRanking(categoryId: String): Response<RankingRecipeData> {
        return apiService.fetchRecipeRanking(ApiKey().getApiKey(), categoryId)
    }
}

interface ApiService {
    @GET("Recipe/CategoryRanking/20170426")
    suspend fun fetchResults(
        @Query("applicationId") applicationId: String,
        @Query("categoryId") categoryId: Int = 30,
    ): Response<ResultResponse>

    //h_ryoが使う関数/カテゴリ取得
    @GET("Recipe/CategoryList/20170426")
    suspend fun fetchRecipeCategory(
        @Query("applicationId") applicationId: String,
        @Query("categoryType") categoryType: String
    ) : Response<Result>

    //h_ryoが使う関数/ランキング取得
    @GET("Recipe/CategoryRanking/20170426")
    suspend fun fetchRecipeRanking(
        @Query("applicationId") applicationId: String,
        @Query("categoryId") categoryId: String
    ) : Response<RankingRecipeData>
}