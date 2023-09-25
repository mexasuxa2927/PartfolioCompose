package com.example.projectcompose.DataMadels

import java.io.Serializable

data class ProductOrder(var productData: ProductData,var count:Int?=1):Serializable
