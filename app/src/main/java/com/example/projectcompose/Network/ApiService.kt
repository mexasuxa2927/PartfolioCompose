package com.example.projectcompose.Network

import com.example.projectcompose.DataMadels.ProductData
import retrofit2.Response
import retrofit2.http.GET
interface ApiService {

    @GET("/product/")
    suspend fun getAllProduct():Response<List<ProductData>>


}