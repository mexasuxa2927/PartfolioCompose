package com.example.projectcompose.DataMadels

import java.io.Serializable


data class ProductData(
    val description: String?="",
    val id: Int?=0,
    val photo: String?="",
    val price: String?="",
    val product_name: String?="",
    val type: String?=""
):Serializable