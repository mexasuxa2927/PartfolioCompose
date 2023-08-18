package com.example.projectcompose.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.projectcompose.DataMadels.ProductData

class SharedViewModel: ViewModel() {
     var sharedata by mutableStateOf<ProductData?>(null)
        private set

    fun setData(productData: ProductData){
        sharedata  = productData
    }
}