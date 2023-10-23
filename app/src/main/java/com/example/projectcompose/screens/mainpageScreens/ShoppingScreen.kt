package com.example.projectcompose.screens.mainpageScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectcompose.DataMadels.ProductOrder
import com.example.projectcompose.R
import com.example.projectcompose.Utils.ActionListner
import com.example.projectcompose.ViewModel.MyViewModel
import com.example.projectcompose.ViewModel.SharedViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ShoppingScreen(actionListner: ActionListner, sharedViewModel: SharedViewModel) {
ScreenUi(actionListner = actionListner, sharedViewModel = sharedViewModel)

}
@Composable
private fun ScreenUi(actionListner: ActionListner, sharedViewModel: SharedViewModel){
    val myViewModel: MyViewModel = hiltViewModel()
    val listOrder = myViewModel.readOrder().observeAsState(initial = Result.failure(Throwable()))
    var data  = remember{ mutableStateListOf<ProductOrder>() }
    val context =  LocalContext.current

    listOrder.value.onSuccess {
        data.clear()
        data.addAll(it)
    }.onFailure {
        data.clear()
        data.addAll(emptyList())
    }


    LazyColumn{
        items(count =data.size){
            ItemViewLazyColumn(data = data[it],data)
        }
        item {
           Row(modifier = Modifier.padding(24.dp)) {
               Column(modifier = Modifier
                   .fillMaxWidth()
                   .height(64.dp).clickable {
                       myViewModel.deleteOrders()
                       data.clear()


                   }
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
        item { 
            Spacer(modifier = Modifier.height(50.dp))
        }
    }

}
@Composable
private fun ItemViewLazyColumn(data: ProductOrder, list: SnapshotStateList<ProductOrder>, ) {
    var viewModel:MyViewModel = hiltViewModel()
    var count  = remember {  mutableStateOf(data.count)}
    Box(modifier = Modifier

        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 24.dp, vertical = 5.dp)){
        Card(
            Modifier
                .fillMaxWidth()
                .height(130.dp), shape = RoundedCornerShape(8), elevation = 8.dp){
            Row(Modifier.fillMaxSize()) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .weight(4f)
                    .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxSize()) {
                        GlideImage(imageModel = {data.productData.photo})
                    }

                }
                Column(modifier = Modifier
                    .fillMaxSize()
                    .weight(5f)
                    .padding(top = 16.dp, start = 10.dp)
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .weight(1f)) {
                        Text(text =data.productData.product_name!!, color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                        Text(text =data.productData.price!!, color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    }
                    Row(
                        Modifier
                            .fillMaxSize()

                            .weight(1f), verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier
                                .width(25.dp)
                                .height(25.dp)
                                .clip(shape = RoundedCornerShape(50))
                                .border(
                                    width = 2.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(50)
                                )
                                .clickable {
                                    count.value = count.value!! + 1
                                    viewModel.addCount(count.value!!, data.productData.id!!)
                                }, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                Text(text = "+")
                            }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = count.value.toString())
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                            .clip(shape = RoundedCornerShape(50))
                            .border(
                                width = 2.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(50)
                            )
                            .clickable {
                                if (count.value!! > 1) {
                                    count.value = count.value!! - 1
                                    viewModel.addCount(count.value!!, data.productData.id!!)
                                }
                            }, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                            Text(text = "-")
                        }

                    }
                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(top = 10.dp)){

                    Icon(painter = painterResource(id = R.drawable.delete_icon), contentDescription ="", tint = colorResource(
                        id = R.color.white_gray
                    ),modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                        .align(
                            Alignment.TopCenter
                        )
                        .clickable {
                            viewModel.removeOrder(data)
                            list.remove(data)
                        } )

                }

            }
        }
    }

}


