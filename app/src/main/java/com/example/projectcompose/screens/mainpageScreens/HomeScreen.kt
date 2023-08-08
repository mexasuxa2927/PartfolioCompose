package com.example.projectcompose.screens.mainpageScreens


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectcompose.DataMadels.ProductData
import com.example.projectcompose.R
import com.example.projectcompose.ViewModel.MyViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeScreen() {
    ScreenUi()
}

@Composable
private fun ScreenUi(){
    val myViewmodel : MyViewModel  = hiltViewModel()
    val data  by myViewmodel.getAllProduct().observeAsState(initial = Result.failure(Throwable()))
    val listdata  = remember{ mutableListOf<ProductData>() }

    data .onSuccess {
        listdata.addAll(it)
        Log.d("@@@@", "SUCCESS:$listdata")
    }.onFailure {
        listdata.addAll(emptyList())
        Log.d("@@@@", "ERROR:$listdata")
    }


    LazyColumn(){
        Log.d("@@@@", "ScreenUi:${listdata.size} ")
        items(count = listdata.size){
            ItemViewList(data =listdata[it],false)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}















@Composable
fun ItemViewList(data: ProductData,liked:Boolean) {
    Column() {
        Card(modifier = Modifier
            .fillMaxWidth()

            .height(250.dp), shape = RoundedCornerShape(5), elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)) {
            Box(modifier = Modifier.fillMaxSize()){
                GlideImage(imageModel = {  data.photo})
                Box(modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .align(Alignment.TopEnd)
                    .padding(top = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(50))
                    .background(color = Color.White)) {
                    Icon(modifier = Modifier
                        .align(Alignment.Center)
                        .width(24.dp)
                        .height(24.dp),painter = painterResource(id = R.drawable.heart_icon), contentDescription ="", tint = if(liked) colorResource(id = R.color.white_gray) else colorResource(id =R.color.orange))
                }
            }
        }
    }
    
}




@Preview(showBackground = true)
@Composable
private fun showUI(){
    ScreenUi()
}