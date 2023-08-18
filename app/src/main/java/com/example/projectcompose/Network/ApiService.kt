package com.example.projectcompose.Network

import com.example.projectcompose.DataMadels.ProductData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/product/")
    suspend fun getAllProduct(@Query("search")string: String):Response<List<ProductData>>


}