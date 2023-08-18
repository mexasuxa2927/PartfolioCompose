package com.example.projectcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.projectcompose.DataMadels.ProductData
import com.example.projectcompose.R
import com.example.projectcompose.ViewModel.SharedViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ItemScreen( sharedViewModel: SharedViewModel) {
    var productData  =  sharedViewModel.sharedata
    ScreenUi(productData = productData!!)

}

@Composable
private fun ScreenUi(productData: ProductData?){
    var navController   = rememberNavController()
   Column(modifier = Modifier.fillMaxSize()) {
       LazyColumn{
           item {
               Column(modifier = Modifier
                   .fillMaxWidth()
                   .weight(8f)
                   .padding(10.dp)) {
                   Card(modifier = Modifier
                       .fillMaxWidth()
                       .clickable {
                       }
                       .height(360.dp), shape = RoundedCornerShape(5), elevation = 8.dp) {
                       Box(modifier = Modifier.fillMaxSize()){
                           GlideImage(imageModel = {productData?.photo})
                       }
                   }

                   Row(modifier = Modifier
                       .fillMaxWidth()
                       .padding(10.dp)) {
                       Column(
                           Modifier
                               .fillMaxWidth()
                               .weight(7f)) {
                           Text(text = productData!!.product_name, fontWeight = FontWeight.Bold, color = Color.Black)
                       }
                       Column(
                           Modifier
                               .fillMaxWidth()
                               .weight(4f)) {
                           Text(text = productData!!.price+" summ", color = colorResource(id = R.color.orange))

                       }
                   }
                   Text(text = productData!!.description)


               }
           }

       }
       Box(
           Modifier
               .fillMaxSize()
               .weight(2f)
               .padding(horizontal = 25.dp)
               .padding(bottom = 25.dp)) {

           Column(modifier = Modifier
               .fillMaxWidth()
               .height(64.dp)
               .align(Alignment.BottomCenter)
               .background(
                   color = colorResource(
                       id = R.color.orange
                   ), shape = RoundedCornerShape(16)
               )
               .clip(shape = RoundedCornerShape(16)), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Add to card", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
           }
       }


   }

}



@Preview
@Composable
private fun ShowUI(){


}



