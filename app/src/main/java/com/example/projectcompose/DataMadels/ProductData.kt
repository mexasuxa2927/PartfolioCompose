package com.example.projectcompose.DataMadels

import java.io.Serializable


data class ProductData(
    val description: String,
    val id: Int,
    val photo: String,
    val price: String,
    val product_name: String,
    val type: String
):Serializable